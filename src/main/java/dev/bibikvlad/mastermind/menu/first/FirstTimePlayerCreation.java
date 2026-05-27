package dev.bibikvlad.mastermind.menu.first;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.model.player.Player;

public class FirstTimePlayerCreation {
    private final LocaleType localeType;
    private final LocalizationContext localizationContext;
    private final ServiceContainer serviceContainer;
    private final Parser parser;
    private final Printer printer;

    public FirstTimePlayerCreation(LocaleType localeType, ServiceContainer serviceContainer,
                                   Parser parser, Printer printer) {
        this.localeType = localeType;
        this.localizationContext = new LocalizationContext(localeType);
        this.serviceContainer = serviceContainer;
        this.parser = parser;
        this.printer = printer;
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
