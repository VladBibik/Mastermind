package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.MainMenu;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

public class PlayerMenu implements Menu {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final PlayerService  playerService;

    private Player currentPlayer;

    public PlayerMenu(LocalizationContext localizationContext, MastermindUserInputParser parser,
                      PlayerService playerService) {
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.playerService = playerService;
        this.currentPlayer = playerService.loadLastSelectedPlayer().orElseThrow(
                () -> new IllegalStateException("No last selected player found!"));
    }

    @Override
    public Menu run() {
        displayMenu();

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

        return selection
                .map(this::menuOptionSwitcher)
                .orElseGet(() -> new MainMenu(localizationContext, parser, playerService));

    }

    private void displayMenu() {
        System.out.println();
        System.out.println("1. Change player");
        System.out.println("2. Change player's name");
        System.out.println("3. Delete current player's data");
        System.out.println("4. Back to main menu");
    }

    private Menu menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> {
                return changePlayer();
            }
            case 2 -> {
                return changePlayerName();
            }
            case 3 -> {
                return deletePlayer();
            }
            case 4 -> {
                return quit();
            }
            default -> {
                System.out.println("Invalid input. Please enter a number corresponding to the menu option.");

                return this;
            }
        }
    }

    private Menu changePlayer() {
        if (!playerService.isMultiplePlayersRegistered()) {
            System.out.println("Please register at least one more player first.");

            return this;
        }

        Menu playerSelectionMenu = new PlayerSelectionMenu(localizationContext, parser, playerService);

        return playerSelectionMenu.run();
    }

    private Menu changePlayerName() {
        return new PlayerNameChanger(localizationContext, parser, playerService);
    }

    private Menu deletePlayer() {
        return new DeletePlayerMenu(localizationContext, parser, playerService);
    }

    private Menu quit() {
        return new MainMenu(localizationContext, parser, playerService);
    }
}

//TODO: FIX DELETE POSITIONING ISSUE!