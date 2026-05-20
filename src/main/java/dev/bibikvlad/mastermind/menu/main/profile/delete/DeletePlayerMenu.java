package dev.bibikvlad.mastermind.menu.main.profile.delete;

import dev.bibikvlad.mastermind.app.context.AppContext;
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
        firstWarning();

        if (isFirstCheckPassed() && isSecondCheckPassed()) {
            return handlePlayerDeletion();
        }

        return new ProfileMenu(appContext);
    }

    private void firstWarning() {
        printer.printMessage("⚠\uFE0F WARNING. You are entering dangerous zone! ⚠\uFE0F");
        printer.printMessage("If you continue with the deletion, all data for the player: "
                + currentPlayer.getPlayerName() + " will be deleted permanently!");

        confirmToContinue();
    }

    private boolean isFirstCheckPassed() {
        printer.printMessage("Are you sure you want to delete a player: " + currentPlayer.getPlayerName()
                + " with all of their data permanently?");
        printer.printMessage("Print 'yes' if you want to continue with deletion process. "
                + "Any other input will return you back to the Profile Menu");

        String userInput = parser.parseUserInput();

        return userInput.equalsIgnoreCase("yes");
    }

    private boolean isSecondCheckPassed() {
        printer.printMessage("⚠\uFE0F This is the last warning!");
        printer.printMessage("If you want to permanently delete a player: " + currentPlayer.getPlayerName()
                + " with all of their data type 'DELETE' (all uppercase). "
                + "Any other input will return you back to the Profile Menu");

        String userInput = parser.parseUserInput();

        return userInput.equals("DELETE");
    }

    private Menu handlePlayerDeletion() {
        playerService.deletePlayer(currentPlayer.getId());

        printer.printMessage("Player with the name: " + currentPlayer.getPlayerName() + " has been deleted.");

        if (!playerService.isMultiplePlayersRegistered()) {
            return handleOnePlayerLeftCase();
        } else {
            printer.printMessage("If you'll close player selection menu, " +
                    "previous last selected player will be picked automatically.");

            confirmToContinue();

            return new PlayerSelectionMenu(appContext);
        }
    }

    private Menu handleOnePlayerLeftCase() {
        Optional<Player> optionalPlayer = playerService.loadLastSelectedPlayer();
        Player player = optionalPlayer.orElseThrow(() -> new PlayerNotFoundException(
                errorMessages.getLastSelectedPlayerNotFound()));

        printer.printMessage("Player with the name: " + player.getPlayerName() + " has been selected.");

        return new ProfileMenu(createUpdatedAppContext(player));
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
