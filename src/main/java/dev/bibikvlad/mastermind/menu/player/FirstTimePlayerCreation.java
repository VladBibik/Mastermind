package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.app.bootstrap.AppContext;
import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.persistence.player.model.Player;

public class FirstTimePlayerCreation {
    private final LocaleType localeType;
    private final LocalizationContext localizationContext;
    private final ServiceContainer serviceContainer;
    private final MastermindUserInputParser parser;
    private final Printer printer;

    public FirstTimePlayerCreation(LocaleType localeType, ServiceContainer serviceContainer,
                                   MastermindUserInputParser parser, Printer printer) {
        this.localeType = localeType;
        this.localizationContext = new LocalizationContext(localeType);
        this.serviceContainer = serviceContainer;
        this.parser = parser;
        this.printer = printer;
    }

    public AppContext createPlayerAndGetContext() {
        System.out.println("Please enter nickname of a new player");
        System.out.println("To cancel type: 'close' or 'exit'");

        while (true) {
            String newPlayerName = parser.parseUserInput();

            if (StringEmptyValidator.isNullOrEmpty(newPlayerName)) {
                System.out.println("Player name cannot be empty");

                continue;
            }

            if (newPlayerName.equalsIgnoreCase("exit") ||
                    newPlayerName.equalsIgnoreCase("close")) {
                return null;
            }

            if (newPlayerName.length() > 100) {
                System.out.println("Player name cannot be longer than 100 characters");

                continue;
            }

            try {
                Player createdPlayer = serviceContainer.getPlayerService()
                        .savePlayerWithProvidedLocale(newPlayerName, localeType);

                System.out.println("Player with name " + newPlayerName + " has been created.\n");

                return new AppContext(localizationContext, serviceContainer, printer, parser,
                        createdPlayer);
            } catch (PlayerAlreadyExistException exception) {
                System.out.println("Player with name " + newPlayerName + " already exists.\n");
            }
        }
    }
}
