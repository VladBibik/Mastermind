package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.app.bootstrap.GamesServiceGeneratorTEMP;
import dev.bibikvlad.mastermind.app.game.MastermindGameLauncher;
import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.player.PlayerMenu;
import dev.bibikvlad.mastermind.menu.settings.SettingsMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.GamesService;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

public class MainMenu implements Menu {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final PlayerService playerService;

    private final Player currentPlayer;

    private final GamesService gamesService;

    //TODO: Need to rethink GamesService injection logic
    public MainMenu(LocalizationContext localizationContext,
                    MastermindUserInputParser parser,
                    PlayerService playerService) {
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.playerService = playerService;
        this.currentPlayer = playerService.loadLastSelectedPlayer().orElseThrow(
                () -> new IllegalStateException("No last selected player found!"));
        this.gamesService = GamesServiceGeneratorTEMP.get();
    }

    @Override
    public Menu run() {
        displayMenu();

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

        if (selection.isEmpty())
            return new ExitMenu();

        return menuOptionSwitcher(selection.get());
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

    //TODO: Think if quiting should be an menu option or leave it as prompt command
    private Menu menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> {
                return newPlayerCreation();
            }
            case 2 -> {
                launchGame();

                return this;
            }
            case 3 -> {
                return playerMenu();
            }
            case 4 -> {
                displayCurrentPlayerData();

                return this;
            }
            case 5 -> {
                return settings();
            }
            default -> {
                System.out.println("Invalid input. Please enter a number corresponding to the menu option.\n");

                return this;
            }
        }
    }

    private Menu newPlayerCreation() {
        return new NewPlayerCreation(parser, playerService, localizationContext,
                currentPlayer.getPlayerConfig().getLocale(), this);
    }

    private void launchGame() {
        MastermindGameLauncher mastermindGameLauncher = new MastermindGameLauncher(localizationContext,
                currentPlayer.getPlayerConfig().getLogoColorsBundle());

        GameData gameData = mastermindGameLauncher.launch();

        gamesService.save(currentPlayer.getId(), gameData);
    }

    private Menu playerMenu() {
        return new PlayerMenu(localizationContext, parser, playerService);
    }

    private void displayCurrentPlayerData() {
        System.out.println("Current Player: " + currentPlayer);
    }

    private Menu settings() {
        return new SettingsMenu(localizationContext, parser, playerService);
    }
}
