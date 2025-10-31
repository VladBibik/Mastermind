package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.app.MastermindGameLauncher;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.player.PlayerMenu;
import dev.bibikvlad.mastermind.menu.settings.SettingsMenu;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

public class MainMenu {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final PlayerService playerService;

    private Player currentPlayer;

    public MainMenu(LocalizationContext localizationContext,
                    MastermindUserInputParser parser,
                    PlayerService playerService) {
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.playerService = playerService;
    }

    public void menu() {
        if (currentPlayer == null) {
            loadLastSelectedPlayer();
        }

        while (true) {
            displayMenu();

            Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

            if (selection.isEmpty())
                break;

            menuOptionSwitcher(selection.get());
        }
    }

    private void displayMenu() {
        System.out.println("Welcome to the Mastermind Game " + currentPlayer.getPlayerName() + "!");
        System.out.println("1. Create a new Player");
        System.out.println("2. Play a new Game");
        System.out.println("3. Player menu");
        System.out.println("4. Current player's data");
        System.out.println("5. Settings");
        System.out.println("To close the game print: 'close', or 'exit'");
    }

    private void menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> newPlayerCreation();
            case 2 -> launchGame();
            case 3 -> playerMenu();
            case 4 -> displayCurrentPlayerData();
            case 5 -> settings();
        }
    }

    private void newPlayerCreation() {
        NewPlayerCreation.create(parser, playerService, currentPlayer.getPlayerConfig().getLocale());

        loadLastSelectedPlayer();
    }

    private void launchGame() {
        MastermindGameLauncher mastermindGameLauncher = new MastermindGameLauncher(localizationContext,
                currentPlayer.getPlayerConfig().getLogoColorsBundle());

        mastermindGameLauncher.launch();
    }

    private void playerMenu() {
        PlayerMenu playerMenu = new PlayerMenu(localizationContext, parser, playerService, currentPlayer);

        playerMenu.menu();

        //TODO: Think of a better way of updating player
        currentPlayer = playerService.loadLastSelectedPlayer().get();
    }

    private void loadLastSelectedPlayer() {
        Optional<Player> lastSelectedPlayer = playerService.loadLastSelectedPlayer();

        currentPlayer = lastSelectedPlayer.get();
    }

    private void displayCurrentPlayerData() {
        System.out.println("Current Player: " + currentPlayer);
    }

    private void settings() {
        SettingsMenu settingsMenu = new SettingsMenu(localizationContext, parser, playerService, currentPlayer);

        settingsMenu.settingsMenu();

        loadLastSelectedPlayer();
    }
}
