package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.services.PlayerService;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;

//TODO: Either pass LocalizationContext, or already created Messages, or make this class a normal instance based class
public class NewPlayerCreation {
    public static boolean create(MastermindUserInputParser parser,
                                        PlayerService playerService,
                                        LocaleType locale) {
        while (true) {
            System.out.println("Please enter nickname of a new player");
            System.out.println("To cancel type: 'close' or 'exit'");

            String newPlayerName = parser.parseUserInput();

            if (StringEmptyValidator.isNullOrEmpty(newPlayerName)) {
                System.out.println("Player name cannot be empty");

                continue;
            }

            if (newPlayerName.equalsIgnoreCase("exit") ||
                    newPlayerName.equalsIgnoreCase("close")) {

                return true;
            }

            if (newPlayerName.length() > 100) {
                System.out.println("Player name cannot be longer than 100 characters");

                continue;
            }

            try {
                if (playerService.savePlayerWithProvidedLocale(newPlayerName, locale)) {
                    System.out.println("Player with name " + newPlayerName + " has been created.\n");

                    return false;
                }

            } catch (PlayerAlreadyExistException exception) {
                System.out.println("Player with name " + newPlayerName + " already exists.\n");
            }
        }
    }
}
