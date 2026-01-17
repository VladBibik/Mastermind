package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.services.PlayerService;

//TODO: Either pass LocalizationContext, or already created Messages, or make this class a normal instance based class
public class NewPlayerCreation extends Menu {
    private final PlayerService playerService;
    private final LocaleType localeType;
    private final Menu returnMenu;

    public NewPlayerCreation(LocalizationContext localizationContext, ServiceContainer serviceContainer,
                             MastermindUserInputParser parser, LocaleType localeType, Menu returnMenu) {
        super(localizationContext, serviceContainer, parser);

        this.playerService = serviceContainer.getPlayerService();
        this.localeType = localeType;
        this.returnMenu = returnMenu;
    }

    @Override
    public Menu run() {
        System.out.println("Please enter nickname of a new player");
        System.out.println("To cancel type: 'close' or 'exit'");

        String newPlayerName = parser.parseUserInput();

        if (StringEmptyValidator.isNullOrEmpty(newPlayerName)) {
            System.out.println("Player name cannot be empty");

            return this;
        }

        if (newPlayerName.equalsIgnoreCase("exit") ||
                newPlayerName.equalsIgnoreCase("close")) {
            return returnMenu;
        }

        if (newPlayerName.length() > 100) {
            System.out.println("Player name cannot be longer than 100 characters");

            return this;
        }

        try {
            if (playerService.savePlayerWithProvidedLocale(newPlayerName, localeType)) {
                System.out.println("Player with name " + newPlayerName + " has been created.\n");

                return returnMenu;
            }

        } catch (PlayerAlreadyExistException exception) {
            System.out.println("Player with name " + newPlayerName + " already exists.\n");
        }

        return this;
    }
}
