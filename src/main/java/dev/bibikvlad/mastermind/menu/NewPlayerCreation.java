package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.services.PlayerService;
import dev.bibikvlad.mastermind.validators.StringEmptyValidator;

//TODO: Either pass LocalizationContext, or already created Messages, or make this class a normal instance based class
public class NewPlayerCreation {
    public static void create(MastermindUserInputParser parser,
                                        PlayerService playerService,
                                        LocaleType locale) {
        boolean isDone = false;

        while (!isDone) {
            System.out.println("Please enter nickname of a new player: ");

            String newPlayerName = parser.parseUserInput();

            if (StringEmptyValidator.isNullOrEmpty(newPlayerName)) {
                System.out.println("Player name cannot be empty");

                continue;
            }

            if (newPlayerName.length() > 100) {
                System.out.println("Player name cannot be longer than 100 characters");

                continue;
            }

            try {
                if (playerService.savePlayerWithProvidedLocale(newPlayerName, locale)) {
                    System.out.println("Player with name " + newPlayerName + " has been created.\n");

                    isDone = true;
                }

            } catch (PlayerAlreadyExistException exception) {
                System.out.println("Player with name " + newPlayerName + " already exists.\n");
            }
        }
    }
}
