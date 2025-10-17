package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.app.MastermindApplication;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

public class GameMenu {
    private final MastermindUserInputParser parser;
    private final PlayerService playerService;

    //TODO: Need to add retrieval and update logic to this field!
    private Player currentPlayer;

    public GameMenu(MastermindUserInputParser parser, PlayerService playerService) {
        this.parser = parser;
        this.playerService = playerService;
    }

    public void menu() {
        if (currentPlayer == null) {
            loadLastSelectedPlayer();
        }

        displayMenu();

        while (true) {
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

            tempMenuSwitch(userInputNumber);
        }
    }

    public void displayMenu() {
        System.out.println("Welcome to the Mastermind Game " + currentPlayer.getPlayerName());
        System.out.println("To create a new Player press: 1");
        System.out.println("To play a new Game press: 2");
        System.out.println("To view all Players press: 3");
        System.out.println("To view again menu options press: 4");
        System.out.println("To view current player's data press: 5");
        System.out.println("To close the game print: 'close', or 'exit'");
    }

    public void tempMenuSwitch(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> createNewPlayer();
            case 2 -> launchGame();
            case 3 -> printAllPlayers();
            case 4 -> displayMenu();
            case 5 -> displayCurrentPlayerDataTEMP();
            default -> System.out.println("Invalid selection");
        }
    }

    public void launchGame() {
        MastermindApplication.main(new String[0]);
    }

    public void createNewPlayer() {
        System.out.println("Please enter the name of the player you would like to create: ");

        String newPlayerName = parser.parseUserInput();

        try {
            if (playerService.savePlayerWithDefaultConfigs(newPlayerName)) {
                System.out.println("Player with name " + newPlayerName + " has been created.\n");
            }

            loadLastSelectedPlayer();
        } catch (PlayerAlreadyExistException exception) {
            System.out.println(exception.getMessage() + "\n" + "You were returned to the main menu");
        }
    }

    public void printAllPlayers() {
        for (Player player : playerService.getAllPlayers()) {
            System.out.println(player);
        }
    }

    private void loadLastSelectedPlayer() {
        Optional<Player> lastSelectedPlayer = playerService.loadLastSelectedPlayer();

        if (lastSelectedPlayer.isPresent()) {
            currentPlayer = playerService.loadLastSelectedPlayer().get();
        } else {
            firstTimeLaunch();
        }
    }

    private void firstTimeLaunch() {
        System.out.println("Welcome to the Mastermind Game!");

        createNewPlayer();
    }

    private void displayCurrentPlayerDataTEMP() {
        System.out.println("Current Player: " + currentPlayer);
    }
}
