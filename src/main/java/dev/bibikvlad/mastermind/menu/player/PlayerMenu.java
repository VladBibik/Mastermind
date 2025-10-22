package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class PlayerMenu {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final PlayerService  playerService;

    private Player currentPlayer;
    private boolean isDone = false;

    public PlayerMenu(LocalizationContext localizationContext, MastermindUserInputParser parser,
                      PlayerService playerService, Player currentPlayer) {
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.playerService = playerService;
        this.currentPlayer = currentPlayer;
    }

    public void menu() {
        while (!isDone) {
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
        System.out.println();
        System.out.println("1. Change player");
        System.out.println("2. Back to main menu");
    }

    private void menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> changePlayer();
        }
    }

    private void changePlayer() {
        PlayerSelectionMenu playerSelectionMenu = new PlayerSelectionMenu(localizationContext, parser, playerService);

        currentPlayer = playerSelectionMenu.selectPlayer();

        playerService.updateLastSelectedPlayer(currentPlayer.getId());
    }
}
