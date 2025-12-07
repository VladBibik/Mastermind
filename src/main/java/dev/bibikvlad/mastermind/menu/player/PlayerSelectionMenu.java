package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.List;

public class PlayerSelectionMenu implements Menu {
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

    @Override
    public Menu run() {
        displayPlayers();
        System.out.println("\nTo get back to previous menu print 'exit', or 'quit'");

        String userInput = parser.parseUserInput();

        if (StringEmptyValidator.isNullOrEmpty(userInput)) {
            System.out.println("Input cannot be empty. Please try again.");

            return this;
        }

        if (userInput.equals("exit") || userInput.equals("quit")) {
            return new PlayerMenu(localizationContext, parser, playerService);
        }

        int userInputNumber;

        try {
            userInputNumber = Integer.parseInt(userInput);
        } catch (NumberFormatException exception) {
            System.out.println("Invalid input. Please enter a number corresponding to the player index.");

            return this;
        }

        Player player = selectPlayer(userInputNumber);

        if (player == null) {
            System.out.println("Invalid input. Please enter a number corresponding to the player index.");

            return this;
        } else {
            playerService.updateLastSelectedPlayer(player.getId());

            System.out.println("Player " + player.getPlayerName() + " has been selected.");
        }

        return new PlayerMenu(localizationContext, parser, playerService);
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
            return null;
        }

        return player;
    }
}
