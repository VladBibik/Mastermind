package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.app.bootstrap.AppContext;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.List;

public class PlayerSelectionMenu extends Menu {
    private final PlayerService playerService;

    public PlayerSelectionMenu(AppContext appContext) {
        super(appContext);

        this.playerService = appContext.services().getPlayerService();
    }

    @Override
    public Menu run() {
        List<Player> playerList = getAllPlayers();

        displayPlayers(playerList);

        System.out.println("\nTo get back to previous menu print 'exit', or 'quit'");

        String userInput = appContext.parser().parseUserInput();

        if (StringEmptyValidator.isNullOrEmpty(userInput)) {
            System.out.println("Input cannot be empty. Please try again.");

            return this;
        }

        if (userInput.equals("exit") || userInput.equals("quit")) {
            return new ProfileMenu(appContext);
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

        AppContext appContext = new AppContext(this.appContext.localizationContext(), this.appContext.services(),
                this.appContext.printer(), this.appContext.parser(),  player);

        return new ProfileMenu(appContext);
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
