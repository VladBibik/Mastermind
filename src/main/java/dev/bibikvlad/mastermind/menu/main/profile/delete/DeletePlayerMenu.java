package dev.bibikvlad.mastermind.menu.main.profile.delete;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.AnsiSafeFormatter;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.exceptions.PlayerNotFoundException;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.error.ErrorMessages;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.profile.ProfileMenu;
import dev.bibikvlad.mastermind.menu.main.profile.selection.PlayerSelectionMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

public class DeletePlayerMenu extends Menu {
    private final Printer printer;
    private final Parser parser;
    private final PlayerService playerService;
    private final Player currentPlayer;
    private final InteractionMessages interactionMessages;
    private final ErrorMessages errorMessages;

    public DeletePlayerMenu(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.playerService = appContext.services().getPlayerService();
        this.currentPlayer = appContext.currentPlayer();
        this.interactionMessages = appContext.localizationContext().getMessages(MessageType.INTERACTION);
        this.errorMessages = appContext.localizationContext().getMessages(MessageType.ERROR);
    }

    @Override
    public Menu run() {
        String playerName = AnsiSafeFormatter.isolate(currentPlayer.getPlayerName());

        printWarning(playerName);

        if (isDeleteCheckPassed(playerName)) {
            return handlePlayerDeletion();
        }

        return new ProfileMenu(appContext);
    }

    private void printWarning(String playerName) {
        printer.printMessage("⚠\uFE0F WARNING. You are entering dangerous zone! ⚠\uFE0F");
        printer.printMessage("If you continue with the deletion, all data for the player: "
                + playerName + " will be deleted permanently!");

        confirmToContinue();
    }

    private boolean isDeleteCheckPassed(String playerName) {
        printer.printMessage("⚠\uFE0F This is the last warning!");
        printer.printMessage("If you want to permanently delete a player: " + playerName
                + " with all of their data type 'DELETE' (all uppercase). "
                + "Any other input will return you back to the Profile Menu");

        String userInput = parser.parseUserInput();

        return userInput.equals("DELETE");
    }

    private Menu handlePlayerDeletion() {
        playerService.deletePlayer(currentPlayer.getId());

        String playerName = AnsiSafeFormatter.isolate(currentPlayer.getPlayerName());

        printer.printMessage("Player with the name: " + playerName + " has been deleted.");

        if (playerService.isMultiplePlayersRegistered()) {
            printSelectionWarning();

            return new PlayerSelectionMenu(appContext);
        } else {
            return selectRemainingPlayer();
        }
    }

    private Menu selectRemainingPlayer() {
        Player player = getLastSelectedPlayer();
        String playerName = AnsiSafeFormatter.isolate(player.getPlayerName());

        printer.printMessage("Player with the name: " + playerName + " has been selected.");

        return new ProfileMenu(createUpdatedAppContext(player));
    }

    private void printSelectionWarning() {
        String playerName = AnsiSafeFormatter.isolate(getLastSelectedPlayer().getPlayerName());

        printer.printMessage("If you'll close player selection menu, " +
                "player: " + playerName + " will be selected automatically.");

        confirmToContinue();
    }

    private Player getLastSelectedPlayer() {
        Optional<Player> optionalPlayer = playerService.loadLastSelectedPlayer();

        return optionalPlayer.orElseThrow(() -> new PlayerNotFoundException(
                errorMessages.getLastSelectedPlayerNotFound()));
    }

    private AppContext createUpdatedAppContext(Player player) {
        return new AppContext(new LocalizationContext(player.getPlayerConfig().locale()),
                this.appContext.services(), this.printer, this.parser, player);
    }

    private void confirmToContinue() {
        printer.printMessage(interactionMessages.getPressEnter());

        parser.parseUserInput();
    }
}
