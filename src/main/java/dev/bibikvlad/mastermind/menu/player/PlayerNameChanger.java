package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;
import dev.bibikvlad.mastermind.validators.StringEmptyValidator;

public class PlayerNameChanger {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final PlayerService playerService;
    private final Player currentPlayer;

    public PlayerNameChanger(LocalizationContext localizationContext, MastermindUserInputParser parser,
                             PlayerService playerService, Player currentPlayer) {
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.playerService = playerService;
        this.currentPlayer = currentPlayer;
    }

    public void menu() {
        while (true) {
            System.out.println("Please enter a new Player's name for: " + currentPlayer.getPlayerName());
            System.out.println();
            System.out.println("To go back to the previous menu enter 'exit' o 'close'");

            String userInput = parser.parseUserInput();

            if (StringEmptyValidator.isNullOrEmpty(userInput)) {
                System.out.println("Player's name cannot be empty");

                continue;
            }

            if (userInput.equalsIgnoreCase("exit") || userInput.equalsIgnoreCase("close")) {
                break;
            }

            try {
                playerService.updatePlayerName(currentPlayer.getId(), userInput);

                break;
            } catch (PlayerAlreadyExistException exception) {
                System.out.println("Player with name " + userInput + " already exists\n");
            }
        }
    }
}
