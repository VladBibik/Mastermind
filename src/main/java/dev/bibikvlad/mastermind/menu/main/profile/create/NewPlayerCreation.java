package dev.bibikvlad.mastermind.menu.main.profile.create;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.input.interpreter.PlayerCreationInput;
import dev.bibikvlad.mastermind.input.interpreter.PlayerCreationInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.PlayerNameValidator;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.create.NewPlayerCreationMenuMessages;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.profile.ProfileMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class NewPlayerCreation extends Menu {
    private final Printer printer;
    private final Parser parser;
    private final PlayerService playerService;
    private final NewPlayerCreationMenuMessages creationMessages;
    private final PlayerNameValidator validator;

    public NewPlayerCreation(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.playerService = appContext.services().getPlayerService();
        this.creationMessages = appContext.localizationContext().getMessages(MessageType.CREATE);
        this.validator = new PlayerNameValidator(printer, creationMessages);
    }

    @Override
    public Menu run() {
        printer.printMessage(creationMessages.getNewPlayerTitle());

        PlayerCreationInput selection = PlayerCreationInputInterpreter.readSelection(parser);

        if (!handleExit(selection)) {
            return new ProfileMenu(appContext);
        }

        String newPlayerName = selection.userInput();

        if (!validator.validateAndPrintErrors(newPlayerName)) {
            return this;
        }

        try {
            return new ProfileMenu(savePlayerAndBuildContext(newPlayerName));
        } catch (PlayerAlreadyExistException exception) {
            printer.printMessage(creationMessages.getPlayerAlreadyExistsError(newPlayerName));
        }

        return this;
    }

    private boolean handleExit(PlayerCreationInput selection) {
        if (selection.isExit()) {
            printer.printMessage(creationMessages.getReservedCommandConfirmation(selection.userInput()));

            String confirmation = parser.parseUserInput();

            return confirmation.equalsIgnoreCase("Yes");
        }

        return true;
    }

    private AppContext savePlayerAndBuildContext(String playerName) {
        Player createdPlayer = playerService.createPlayer(playerName,
                appContext.currentPlayer().getPlayerConfig().locale());

        printer.printMessage(creationMessages.getPlayerCreatedSuccess(playerName));

        return new AppContext(this.appContext.localizationContext(), this.appContext.services(),
                this.appContext.printer(), this.appContext.parser(), createdPlayer);
    }
}
