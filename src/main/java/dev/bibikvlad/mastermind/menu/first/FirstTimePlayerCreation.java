package dev.bibikvlad.mastermind.menu.first;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.PlayerNameValidator;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.create.NewPlayerCreationMenuMessages;
import dev.bibikvlad.mastermind.menu.main.profile.create.PlayerNameValidationResult;
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
        printer.printMessage("Please enter nickname of a new player");
        printer.printMessage("To cancel type: 'close' or 'exit'");

        while (true) {
            String newPlayerName = parser.parseUserInput();

            if (!isPlayerNameValid(newPlayerName)) {
                continue;
            }

            if (newPlayerName.equalsIgnoreCase("exit") ||
                    newPlayerName.equalsIgnoreCase("close")) {
                return null;
            }

            try {
                Player createdPlayer = serviceContainer.getPlayerService()
                        .savePlayerWithProvidedLocale(newPlayerName, localeType);

                printer.printMessage("Player with name " + newPlayerName + " has been created.\n");

                return new AppContext(localizationContext, serviceContainer, printer, parser,
                        createdPlayer);
            } catch (PlayerAlreadyExistException exception) {
                printer.printMessage("Player with name " + newPlayerName + " already exists.\n");
            }
        }
    }

    private boolean isPlayerNameValid(String newPlayerName) {
        PlayerNameValidator validator = new PlayerNameValidator(creationMessages);
        PlayerNameValidationResult result = validator.validate(newPlayerName);

        if (!result.valid()) {
            printer.printMessage(result.message());
        }

        return result.valid();
    }
}
