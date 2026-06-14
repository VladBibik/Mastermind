package dev.bibikvlad.mastermind.menu.first;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.GlobalMenuCommands;
import dev.bibikvlad.mastermind.input.interpreter.PlayerCreationInput;
import dev.bibikvlad.mastermind.input.interpreter.PlayerCreationInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.PlayerNameValidator;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.create.NewPlayerCreationMenuMessages;
import dev.bibikvlad.mastermind.model.player.Player;

public class FirstTimePlayerCreation {
    private final Parser parser;
    private final Printer printer;
    private final LocaleType localeType;
    private final LocalizationContext localizationContext;
    private final ServiceContainer serviceContainer;
    private final NewPlayerCreationMenuMessages creationMessages;
    private final PlayerNameValidator validator;

    public FirstTimePlayerCreation(Parser parser, Printer printer, LocaleType localeType,
                                   ServiceContainer serviceContainer) {
        this.parser = parser;
        this.printer = printer;
        this.localeType = localeType;
        this.localizationContext = new LocalizationContext(localeType);
        this.serviceContainer = serviceContainer;
        this.creationMessages = localizationContext.getMessages(MessageType.CREATE);
        this.validator = new PlayerNameValidator(printer, creationMessages);
    }

    public AppContext createPlayerAndGetContext() {
        printer.printMessage(creationMessages.getNewPlayerTitle());

        while (true) {
            PlayerCreationInput selection = PlayerCreationInputInterpreter.readSelection(parser);

            if (!handleExit(selection)) {
                return null;
            }

            String playerName = selection.userInput();

            if (!validator.validateAndPrintErrors(playerName)) {
                continue;
            }

            return savePlayerAndBuildContext(playerName);
        }
    }

    private boolean handleExit(PlayerCreationInput selection) {
        if (selection.isExit()) {
            printer.printMessage(creationMessages.getReservedCommandConfirmation(selection.userInput()));

            String confirmation = parser.parseUserInput();
            confirmation = confirmation.trim().toLowerCase();

            return GlobalMenuCommands.YES.contains(confirmation);
        }

        return true;
    }

    private AppContext savePlayerAndBuildContext(String playerName) {
        Player createdPlayer = serviceContainer.getPlayerService()
                .createPlayer(playerName, localeType);

        printer.printMessage(creationMessages.getPlayerCreatedSuccess(playerName));

        return new AppContext(localizationContext, serviceContainer, printer, parser,
                createdPlayer);
    }
}
