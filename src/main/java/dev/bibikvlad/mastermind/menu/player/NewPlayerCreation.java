package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.MainMenu;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.services.PlayerService;

//TODO: Either pass LocalizationContext, or already created Messages, or make this class a normal instance based class
public class NewPlayerCreation implements Menu {
    private final MastermindUserInputParser parser;
    private final PlayerService playerService;
    private final LocalizationContext  localizationContext;
    private final LocaleType localeType;
    private final Menu onCancelMenu;

    public NewPlayerCreation(MastermindUserInputParser parser, PlayerService playerService,
                             LocalizationContext localizationContext, LocaleType localeType, Menu onCancelMenu) {
        this.parser = parser;
        this.playerService = playerService;
        this.localizationContext = localizationContext;
        this.localeType = localeType;
        this.onCancelMenu = onCancelMenu;
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
            return onCancelMenu;
        }

        if (newPlayerName.length() > 100) {
            System.out.println("Player name cannot be longer than 100 characters");

            return this;
        }

        try {
            if (playerService.savePlayerWithProvidedLocale(newPlayerName, localeType)) {
                System.out.println("Player with name " + newPlayerName + " has been created.\n");

                return new MainMenu(localizationContext, parser, playerService);
            }

        } catch (PlayerAlreadyExistException exception) {
            System.out.println("Player with name " + newPlayerName + " already exists.\n");
        }

        return this;
    }
}
