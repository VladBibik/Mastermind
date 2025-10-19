package dev.bibikvlad.mastermind.menu.settings;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class SettingsMenu {
    private final Player currentPlayer;
    private final PlayerService playerService;
    private final MastermindUserInputParser parser;

    public SettingsMenu(Player currentPlayer, PlayerService playerService, MastermindUserInputParser parser) {
        this.currentPlayer = currentPlayer;
        this.playerService = playerService;
        this.parser = parser;
    }

    public void settingsMenu() {
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
        System.out.println("1. Change language");
        System.out.println("2. Back to the main menu");
    }

    private void menuOptionSwitcher(int userInputNumber) {
    }
}
