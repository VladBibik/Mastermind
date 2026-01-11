package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.app.game.MastermindGameBootstrap;
import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.games.LeaderboardMenu;
import dev.bibikvlad.mastermind.menu.player.NewPlayerCreation;
import dev.bibikvlad.mastermind.menu.player.PlayerMenu;
import dev.bibikvlad.mastermind.menu.settings.SettingsMenu;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.services.GamesService;
import dev.bibikvlad.mastermind.services.PlayerService;
import dev.bibikvlad.mastermind.services.PlayerStatisticsService;
import dev.bibikvlad.utils.formatters.TimeToStringFormatter;

import java.util.Optional;

public class MainMenu extends Menu {
    private final Player currentPlayer;
    private final GamesService gamesService;
    private final PlayerService playerService;

    //TODO: Need to rethink GamesService injection logic
    public MainMenu(LocalizationContext localizationContext, ServiceContainer serviceContainer,
                    MastermindUserInputParser parser) {
        super(localizationContext, serviceContainer, parser);

        this.playerService = serviceContainer.getPlayerService();
        this.currentPlayer = playerService.loadLastSelectedPlayer().orElseThrow(
                () -> new IllegalStateException("No last selected player found!"));
        this.gamesService = serviceContainer.getGamesService();
    }

    @Override
    public Menu run() {
        displayMenu();

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

        if (selection.isEmpty())
            return new ExitMenu(localizationContext, serviceContainer, parser);

        return menuOptionSwitcher(selection.get());
    }

    private void displayMenu() {
        System.out.println("Welcome to the Mastermind Game " + currentPlayer.getPlayerName() + "!");
        System.out.println("1. Create a new Player");
        System.out.println("2. Play a new Game");
        System.out.println("3. Player menu");
        System.out.println("4. Current player's statistics");
        System.out.println("5. Leaderboards");
        System.out.println("6. Settings");
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
                return leaderboards();
            }
            case 6 -> {
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
                currentPlayer.getPlayerConfig().locale(), this);
    }

    private void launchGame() {
        MastermindGameBootstrap mastermindGameLauncher = new MastermindGameBootstrap(localizationContext,
                currentPlayer.getPlayerConfig().logoColorsBundle());

        GameData gameData = mastermindGameLauncher.launch();

        gamesService.save(currentPlayer.getId(), gameData);
    }

    private Menu playerMenu() {
        return new PlayerMenu(localizationContext, parser, playerService);
    }

    //TODO: Move to separate class
    //TODO: Think if 'press any key to continue' needed, because currently menu options are printed right after statistics, and it makes reading statistics harder
    private void displayCurrentPlayerData() {
        PlayerStatisticsService playerStatisticsService = serviceContainer.getPlayerStatisticsService();

        playerStatisticsService.getPlayerStatistics(currentPlayer.getId()).ifPresent(playerStatistics -> {
            System.out.println();
            System.out.println(currentPlayer.getPlayerName() + "'s statistics:");
            System.out.println("Games played: " + playerStatistics.gameCount());
            System.out.println("Number of wins: " + playerStatistics.winCount());
            System.out.println("Win percentage: " + playerStatistics.winPercentage());
            System.out.println("Total play time: " + TimeToStringFormatter.format(playerStatistics.totalPlayTime()));
            System.out.println("Average game duration: " + TimeToStringFormatter.format(
                    playerStatistics.averageGameDuration()));
            System.out.println("Fastest win time: " + TimeToStringFormatter.format(playerStatistics.fastestWinTime()));
            System.out.println("Minimum turns needed for a win: " + playerStatistics.minTurnsWin());
            System.out.println();
        });
    }

    private Menu leaderboards() {
        return new LeaderboardMenu(localizationContext, parser, playerService, currentPlayer);
    }

    private Menu settings() {
        return new SettingsMenu(localizationContext, serviceContainer, parser);
    }
}
