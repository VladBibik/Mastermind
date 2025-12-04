package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

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

            Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

            if (selection.isEmpty())
                break;

            menuOptionSwitcher(selection.get());
        }
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("1. Change player");
        System.out.println("2. Change player's name");
        System.out.println("3. Delete current player's data");
        System.out.println("4. Back to main menu");
    }

    private void menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> changePlayer();
            case 2 -> changePlayerName();
            case 3 -> deletePlayer();
            case 4 -> quit();
            default -> System.out.println("Invalid input. Please enter a number corresponding to the menu option.");
        }
    }

    private void changePlayer() {
        if (!playerService.isMultiplePlayersRegistered()) {
            System.out.println("Please register at least one more player first.");

            return;
        }

        PlayerSelectionMenu playerSelectionMenu = new PlayerSelectionMenu(localizationContext, parser, playerService);

        Optional<Player> selectedOptionalPlayer = playerSelectionMenu.selectPlayer();

        if (selectedOptionalPlayer.isPresent()) {
            Player selectedPlayer = selectedOptionalPlayer.get();

            if (selectedPlayer.getPlayerName().equals(currentPlayer.getPlayerName())) {
                return;
            }

            playerService.updateLastSelectedPlayer(selectedPlayer.getId());
        }
    }

    private void changePlayerName() {
        PlayerNameChanger playerNameChangeMenu =
                new PlayerNameChanger(localizationContext, parser, playerService, currentPlayer);

        playerNameChangeMenu.menu();
    }

    private void deletePlayer() {
        DeletePlayerMenu deletePlayerMenu = new DeletePlayerMenu(localizationContext, parser,
                playerService, currentPlayer);

        if (deletePlayerMenu.menu()) {
            currentPlayer = playerService.loadLastSelectedPlayer().get();
        }
    }

    private void quit() {
        isDone = true;
    }
}

//TODO: FIX DELETE POSITIONING ISSUE!