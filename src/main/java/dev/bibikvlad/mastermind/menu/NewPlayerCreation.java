package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.services.PlayerService;
import dev.bibikvlad.mastermind.validators.StringEmptyValidator;

public class NewPlayerCreation {
    public static void create(MastermindUserInputParser parser, PlayerService playerService) {
        System.out.println("Please enter the name of the player you would like to create: ");

        String newPlayerName = parser.parseUserInput();

        if (StringEmptyValidator.isNullOrEmpty(newPlayerName)) {
            System.out.println("Player name cannot be empty");

            create(parser, playerService);
        }

        try {
            if (playerService.savePlayerWithDefaultConfigs(newPlayerName)) {
                System.out.println("Player with name " + newPlayerName + " has been created.\n");
            }
            
        } catch (PlayerAlreadyExistException exception) {
            System.out.println(exception.getMessage() + "\n" + "You were returned to the main menu");
        }
    }
}
