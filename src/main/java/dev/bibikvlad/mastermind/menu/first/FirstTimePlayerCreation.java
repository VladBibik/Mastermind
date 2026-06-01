package dev.bibikvlad.mastermind.menu.first;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
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
    private final NewPlayerCreationMenuMessages newPlayerCreationMenuMessages;

    public FirstTimePlayerCreation(Parser parser, Printer printer, LocaleType localeType,
                                   ServiceContainer serviceContainer) {
        this.parser = parser;
        this.printer = printer;
        this.localeType = localeType;
        this.localizationContext = new LocalizationContext(localeType);
        this.serviceContainer = serviceContainer;
        this.newPlayerCreationMenuMessages = localizationContext.getMessages(MessageType.CREATE);
    }

    public AppContext createPlayerAndGetContext() {
        printer.printMessage("Please enter nickname of a new player");
        printer.printMessage("To cancel type: 'close' or 'exit'");

        while (true) {
            String newPlayerName = parser.parseUserInput();

            if (isInvalidPlayerName(newPlayerName)) {
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

    private boolean isInvalidPlayerName(String playerName) {
        final int MAX_NAME_LENGTH = 100;

        if (StringEmptyValidator.isNullOrEmpty(playerName)) {
            printer.printMessage("Player name cannot be empty");

            return true;
        }

        if (playerName.length() > MAX_NAME_LENGTH) {
            printer.printMessage("Player name cannot be longer than 100 characters");

            return true;
        }

        return false;
    }
}
