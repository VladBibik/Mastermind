package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.app.MastermindApplication;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class GameMenu {
    private final MastermindUserInputParser parser;
    private final PlayerService playerService;

    public GameMenu(MastermindUserInputParser parser, PlayerService playerService) {
        this.parser = parser;
        this.playerService = playerService;
    }

    public void displayMenu() {
        System.out.println("Welcome to the Mastermind Game!");
        System.out.println("To create a new Player press: 1");
        System.out.println("To play a new Game press: 2");
        System.out.println("To view all Players press: 3");
        System.out.println("To close the game print: 'close', or 'exit'");

        tempMenuSwitch();
    }

    public void tempMenuSwitch() {
        String userInput = parser.parseUserInput();

        int selectedNumber = Integer.parseInt(userInput);

        switch (selectedNumber)  {
            case 1 -> createNewPlayer();
            case 2 -> launchGame();
            case 3 -> printAllPlayers();
            default -> System.out.println("Invalid selection");
        }
    }

    public void launchGame() {
        MastermindApplication.main(new String[0]);
    }

    public void createNewPlayer() {
        String newPlayerName = parser.parseUserInput();

        try {
            playerService.savePlayerWithDefaultConfigs(newPlayerName);
        } catch (PlayerAlreadyExistException exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void printAllPlayers() {
        for (Player player : playerService.getAllPlayers()) {
            System.out.println(player);
        }
    }
}
