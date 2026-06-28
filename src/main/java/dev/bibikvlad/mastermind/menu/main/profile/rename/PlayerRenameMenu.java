package dev.bibikvlad.mastermind.menu.main.profile.rename;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.exceptions.PlayerNotFoundException;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.PlayerNameValidator;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.error.ErrorMessages;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.name.PlayerNameMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.rename.PlayerRenameMenuMessages;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.profile.PlayerNameReader;
import dev.bibikvlad.mastermind.menu.main.profile.ProfileMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

public class PlayerRenameMenu extends Menu {
    private final Printer printer;
    private final Parser parser;
    private final PlayerService playerService;
    private final Player currentPlayer;
    private final PlayerRenameMenuMessages renameMessages;
    private final PlayerNameMessages nameMessages;
    private final InteractionMessages interactionMessages;
    private final ErrorMessages errorMessages;
    private final PlayerNameValidator validator;

    public PlayerRenameMenu(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.playerService = appContext.services().getPlayerService();
        this.currentPlayer = appContext.currentPlayer();
        this.renameMessages = appContext.localizationContext().getMessages(MessageType.RENAME);
        this.nameMessages = appContext.localizationContext().getMessages(MessageType.PLAYER_NAME);
        this.interactionMessages = appContext.localizationContext().getMessages(MessageType.INTERACTION);
        this.errorMessages = appContext.localizationContext().getMessages(MessageType.ERROR);
        this.validator = new PlayerNameValidator(printer, nameMessages);
    }

    @Override
    public Menu run() {
        printer.printMessage(renameMessages.getPlayerNameRenameTitle(currentPlayer.getPlayerName()));

        PlayerNameReader playerNameReader = new PlayerNameReader(parser, printer, nameMessages);

        Optional<String> optionalPlayerName = playerNameReader.readPlayerName();

        if (optionalPlayerName.isEmpty()) {
            return new ProfileMenu(appContext);
        }

        String playerName = optionalPlayerName.get();

        if (!validator.validateAndPrintErrors(playerName)) {
            return this;
        }

        try {
            playerService.updatePlayerName(currentPlayer.getId(), playerName);

            AppContext updatedAppContext = createNewAppContext();

            printer.printMessage(renameMessages.getPlayerNameRenameSuccess(
                    updatedAppContext.currentPlayer().getPlayerName()));

            confirmToContinue();

            return new ProfileMenu(updatedAppContext);
        } catch (PlayerAlreadyExistException _) {
            printer.printMessage(nameMessages.getPlayerAlreadyExistsError(playerName));
        }

        return this;
    }

    private AppContext createNewAppContext() {
        Player player = playerService.loadLastSelectedPlayer().orElseThrow(
                () -> new PlayerNotFoundException(errorMessages.getLastSelectedPlayerNotFound()));

        return new AppContext(appContext.localizationContext(), appContext.services(), printer, parser, player);
    }

    private void confirmToContinue() {
        printer.printMessage(interactionMessages.getPressEnter());

        parser.parseUserInput();
    }
}
