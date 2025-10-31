package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;

import java.util.List;

public class PlayerSelectionMenu {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final PlayerService playerService;

    private List<Player> playerList;

    public PlayerSelectionMenu(LocalizationContext localizationContext, MastermindUserInputParser parser,
                               PlayerService playerService) {
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.playerService = playerService;
    }

    public Player selectPlayer() {
        while (true) {
            displayPlayers();
            System.out.println("\nTo get back to previous menu print 'exit', or 'quit'");

            String userInput = parser.parseUserInput();

            if (StringEmptyValidator.isNullOrEmpty(userInput)) {
                System.out.println("Input cannot be empty. Please try again.");

                continue;
            }

            if (userInput.equals("exit") || userInput.equals("quit")) {
                return null;
            }

            int userInputNumber;

            try {
                userInputNumber = Integer.parseInt(userInput);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input. Please enter a number corresponding to the player index.");

                continue;
            }

            Player player = selectPlayer(userInputNumber);

            if (player == null) {
                System.out.println("Invalid input. Please enter a number corresponding to the player index.");

                continue;
            }

            return player;
        }
    }

    private void displayPlayers() {
        playerList = playerService.getAllPlayers();

        for (int i = 0; i < playerList.size(); i++) {
            System.out.println((i + 1) + ": " + playerList.get(i).getPlayerName());
        }
    }

    private Player selectPlayer(int playerIndex) {
        Player player;

        try {
            player = playerList.get(playerIndex - 1);
        } catch (IndexOutOfBoundsException exception) {
            return  null;
        }

        return player;
    }
}
