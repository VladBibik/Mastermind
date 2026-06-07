package dev.bibikvlad.mastermind.menu.first;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.PlayerCreationInputInterpreter;
import dev.bibikvlad.mastermind.input.interpreter.PlayerCreationSelection;
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

    public FirstTimePlayerCreation(Parser parser, Printer printer, LocaleType localeType,
                                   ServiceContainer serviceContainer) {
        this.parser = parser;
        this.printer = printer;
        this.localeType = localeType;
        this.localizationContext = new LocalizationContext(localeType);
        this.serviceContainer = serviceContainer;
        this.creationMessages = localizationContext.getMessages(MessageType.CREATE);
    }

    public AppContext createPlayerAndGetContext() {
        printer.printMessage(creationMessages.getNewPlayerTitle());

        while (true) {
            PlayerCreationSelection selection = PlayerCreationInputInterpreter.readSelection(parser);
            String userName = selection.userInput();

            if (selection.isExit()) {
                printer.printMessage("Are you sure you want to close a player creation menu?");
                printer.printMessage("If this menu is closed the data related to locale that you already selected "
                        + "will be lost, and you'll need to choose language again on the next launch");
                printer.printMessage("To proceed press 'Enter'");
                printer.printMessage("If you want to create a player with the name: " + userName
                        + " print 'Yes'");

                String confirmation = parser.parseUserInput();

                if (!confirmation.equalsIgnoreCase("Yes")) {
                    return null;
                }
            }

            if (!isPlayerNameValid(userName)) {
                continue;
            }

            return savePlayerAndBuildContext(userName);
        }
    }

    private boolean isPlayerNameValid(String newPlayerName) {
        PlayerNameValidator validator = new PlayerNameValidator(printer, creationMessages);

        return validator.validate(newPlayerName);
    }

    private AppContext savePlayerAndBuildContext(String playerName) {
        Player createdPlayer = serviceContainer.getPlayerService()
                .savePlayerWithProvidedLocale(playerName, localeType);

        printer.printMessage(creationMessages.getPlayerCreatedSuccess(playerName));

        return new AppContext(localizationContext, serviceContainer, printer, parser,
                createdPlayer);
    }
}
