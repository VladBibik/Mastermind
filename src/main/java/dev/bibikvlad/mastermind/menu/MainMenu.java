package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.app.MastermindGameLauncher;
import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

public class MainMenu {
    private final MastermindUserInputParser parser;
    private final PlayerService playerService;

    private MastermindGameLauncher mastermindGameLauncher;
    private Player currentPlayer;

    public MainMenu(MastermindUserInputParser parser, PlayerService playerService) {
        this.parser = parser;
        this.playerService = playerService;
    }

    public void menu() {
        if (currentPlayer == null) {
            loadLastSelectedPlayer();
        }

        while (true) {
            displayMenu();

            String userInput = parser.parseUserInput();

            if (userInput.equalsIgnoreCase("exit") || userInput.equalsIgnoreCase("close")) {
                break;
            }

            int userInputNumber;

            try {
                userInputNumber = Integer.parseInt(userInput);
            } catch (NumberFormatException exception) {
                System.out.println("Invalid input. Please enter a number corresponding to the menu option.");

                continue;
            }

            menuOptionSwitcher(userInputNumber);
        }
    }

    private void displayMenu() {
        System.out.println("Welcome to the Mastermind Game " + currentPlayer.getPlayerName());
        System.out.println("To create a new Player press: 1");
        System.out.println("To play a new Game press: 2");
        System.out.println("To view all Players press: 3");
        System.out.println("To view current player's data press: 4");
        System.out.println("To close the game print: 'close', or 'exit'");
    }

    private void menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> newPlayerCreation();
            case 2 -> launchGame();
            case 3 -> printAllPlayers();
            case 4 -> displayCurrentPlayerData();
            default -> System.out.println("Invalid selection");
        }
    }

    private void newPlayerCreation() {
        NewPlayerCreation.create(parser, playerService, currentPlayer.getPlayerConfig().getLocale());

        loadLastSelectedPlayer();
    }

    private void launchGame() {
        mastermindGameLauncher.launch();
    }

    private void printAllPlayers() {
        for (Player player : playerService.getAllPlayers()) {
            System.out.println(player);
        }
    }

    private void loadLastSelectedPlayer() {
        Optional<Player> lastSelectedPlayer = playerService.loadLastSelectedPlayer();

        if (lastSelectedPlayer.isPresent()) {
            currentPlayer = playerService.loadLastSelectedPlayer().get();
            mastermindGameLauncher = new MastermindGameLauncher(currentPlayer.getPlayerConfig().getLocale(),
                    currentPlayer.getPlayerConfig().getLogoColorsBundle());
        } else {
            FirstTimeLaunch.launch(parser, playerService);

            loadLastSelectedPlayer();
        }
    }

    private void displayCurrentPlayerData() {
        System.out.println("Current Player: " + currentPlayer);
    }
}
