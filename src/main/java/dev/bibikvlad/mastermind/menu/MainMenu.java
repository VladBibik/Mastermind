package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.app.bootstrap.AppContext;
import dev.bibikvlad.mastermind.app.game.MastermindGameBootstrap;
import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.menu.games.LeaderboardMenu;
import dev.bibikvlad.mastermind.menu.player.ProfileMenu;
import dev.bibikvlad.mastermind.menu.settings.SettingsMenu;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.services.GamesService;
import dev.bibikvlad.mastermind.services.PlayerStatisticsService;
import dev.bibikvlad.utils.formatters.TimeToStringFormatter;

import java.util.Optional;

public class MainMenu extends Menu {
    private final Player currentPlayer;

    //TODO: Need to rethink GamesService injection logic
    public MainMenu(AppContext appContext) {
        super(appContext);

        this.currentPlayer = appContext.currentPlayer();
    }

    @Override
    public Menu run() {
        displayMenu();

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(appContext.parser());

        return selection
                .map(this::menuOptionSwitcher)
                .orElse(null);

    }

    private void displayMenu() {
        System.out.println("Welcome to the Mastermind Game " + currentPlayer.getPlayerName() + "!");
        System.out.println("1. Play");
        System.out.println("2. Leaderboards");
        System.out.println("3. Stats");
        System.out.println("4. Profile");
        System.out.println("5. Settings");
        System.out.println("6. Exit");
    }

    //TODO: Think if quiting should be an menu option or leave it as prompt command
    private Menu menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> {
                launchGame();

                return this;
            }
            case 2 -> {
                return leaderboards();
            }
            case 3 -> {
                displayCurrentPlayerData();

                return this;
            }
            case 4 -> {
                return profileMenu();
            }
            case 5 -> {
                return settings();
            }
            case 6 -> {
                return exit();
            }
            default -> {
                System.out.println("Invalid input. Please enter a number corresponding to the menu option.\n");

                return this;
            }
        }
    }

    //TODO: This method needs refactoring. Either move it to the custom class, or split in multiple methods
    private void launchGame() {
        MastermindGameBootstrap mastermindGameLauncher = new MastermindGameBootstrap(appContext.localizationContext(),
                currentPlayer.getPlayerConfig().logoColorsBundle());

        GameData gameData = mastermindGameLauncher.launch();

        GamesService gamesService = appContext.services().getGamesService();

        gamesService.save(currentPlayer.getId(), gameData);
    }

    private Menu profileMenu() {
        return new ProfileMenu(appContext);
    }

    //TODO: Move to separate class
    //TODO: Think if 'press any key to continue' needed, because currently menu options are printed right after statistics, and it makes reading statistics harder
    private void displayCurrentPlayerData() {
        PlayerStatisticsService playerStatisticsService = appContext.services().getPlayerStatisticsService();

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
        return new LeaderboardMenu(appContext);
    }

    private Menu settings() {
        return new SettingsMenu(appContext);
    }

    private Menu exit() {
        return null;
    }
}
