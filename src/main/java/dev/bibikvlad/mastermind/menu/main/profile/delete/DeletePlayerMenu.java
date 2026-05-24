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
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.delete.DeletePlayerMenuMessages;
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
    private final DeletePlayerMenuMessages deletePlayerMenuMessages;
    private final InteractionMessages interactionMessages;
    private final ErrorMessages errorMessages;

    public DeletePlayerMenu(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.playerService = appContext.services().getPlayerService();
        this.currentPlayer = appContext.currentPlayer();
        this.deletePlayerMenuMessages = appContext.localizationContext().getMessages(MessageType.DELETE);
        this.interactionMessages = appContext.localizationContext().getMessages(MessageType.INTERACTION);
        this.errorMessages = appContext.localizationContext().getMessages(MessageType.ERROR);
    }

    @Override
    public Menu run() {
        String playerName = AnsiSafeFormatter.isolate(currentPlayer.getPlayerName());

        printDeletionWarning(playerName);

        if (isDeletionConfirmed(playerName)) {
            return handlePlayerDeletion();
        }

        return new ProfileMenu(appContext);
    }

    private void printDeletionWarning(String playerName) {
        printer.printMessage(deletePlayerMenuMessages.getFirstWarning(playerName));

        confirmToContinue();
    }

    private boolean isDeletionConfirmed(String playerName) {
        printer.printMessage(deletePlayerMenuMessages.getDeleteConfirmation(playerName));

        String userInput = parser.parseUserInput();

        return userInput.equals("DELETE");
    }

    private Menu handlePlayerDeletion() {
        playerService.deletePlayer(currentPlayer.getId());

        String playerName = AnsiSafeFormatter.isolate(currentPlayer.getPlayerName());

        printer.printMessage(deletePlayerMenuMessages.getPlayerDeleted(playerName));

        if (playerService.isMultiplePlayersRegistered()) {
            showSelectionWarning();

            return new PlayerSelectionMenu(appContext);
        }

        return selectRemainingPlayer();
    }

    private Menu selectRemainingPlayer() {
        Player player = getLastSelectedPlayer();
        String playerName = AnsiSafeFormatter.isolate(player.getPlayerName());

        printer.printMessage(deletePlayerMenuMessages.getPlayerAutoSelected(playerName));

        return new ProfileMenu(createUpdatedAppContext(player));
    }

    private void showSelectionWarning() {
        String playerName = AnsiSafeFormatter.isolate(getLastSelectedPlayer().getPlayerName());

        printer.printMessage(deletePlayerMenuMessages.getAutoSelectionWarning(playerName));

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
