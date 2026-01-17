package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.List;

public class PlayerSelectionMenu extends Menu {
    private final PlayerService playerService;

    public PlayerSelectionMenu(LocalizationContext localizationContext, ServiceContainer serviceContainer,
                               MastermindUserInputParser parser) {
        super(localizationContext, serviceContainer, parser);

        this.playerService = serviceContainer.getPlayerService();
    }

    @Override
    public Menu run() {
        List<Player> playerList = getAllPlayers();

        displayPlayers(playerList);

        System.out.println("\nTo get back to previous menu print 'exit', or 'quit'");

        String userInput = parser.parseUserInput();

        if (StringEmptyValidator.isNullOrEmpty(userInput)) {
            System.out.println("Input cannot be empty. Please try again.");

            return this;
        }

        if (userInput.equals("exit") || userInput.equals("quit")) {
            return new ProfileMenu(localizationContext, serviceContainer, parser);
        }

        int userInputNumber;

        try {
            userInputNumber = Integer.parseInt(userInput);
        } catch (NumberFormatException exception) {
            System.out.println("Invalid input. Please enter a number corresponding to the player index.");

            return this;
        }

        Player player = selectPlayer(playerList, userInputNumber);

        if (player == null) {
            System.out.println("Invalid input. Please enter a number corresponding to the player index.");

            return this;
        } else {
            playerService.updateLastSelectedPlayer(player.getId());

            System.out.println("Player " + player.getPlayerName() + " has been selected.");
        }

        return new ProfileMenu(localizationContext, serviceContainer, parser);
    }

    private List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    private void displayPlayers(List<Player> playerList) {
        for (int i = 0; i < playerList.size(); i++) {
            System.out.println((i + 1) + ": " + playerList.get(i).getPlayerName());
        }
    }

    private Player selectPlayer(List<Player> playerList, int playerIndex) {
        Player player;

        try {
            player = playerList.get(playerIndex - 1);
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }

        return player;
    }
}
