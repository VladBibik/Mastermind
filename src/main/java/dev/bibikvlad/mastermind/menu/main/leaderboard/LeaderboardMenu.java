package dev.bibikvlad.mastermind.menu.main.leaderboard;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.MainMenu;
import dev.bibikvlad.mastermind.persistence.leaderboard.model.*;
import dev.bibikvlad.mastermind.services.LeaderboardService;
import dev.bibikvlad.utils.formatters.ClockDisplayFormatter;

import java.util.List;
import java.util.Optional;

public class LeaderboardMenu extends Menu {
    private final LeaderboardService leaderboardService;

    public LeaderboardMenu(AppContext appContext) {
        super(appContext);

        this.leaderboardService = appContext.services().getLeaderboardService();
    }

    @Override
    public Menu run() {
        displayMenu();

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(appContext.parser());

        return selection
                .map(this::menuOptionSwitcher)
                .orElseGet(() -> new MainMenu(appContext));
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("1. Leaderboard");
        System.out.println("2. Leaderboard based on time needed for a win");
        System.out.println("3. Leaderboard based on number of turns needed for a win");
        System.out.println("4. Win percentage leaderboard");
        System.out.println("5. Leaderboard based on the number of wins");
        System.out.println("0: Back to the main menu");
    }

    private Menu menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> {
                return printLeaderboard();
            }
            case 2 -> {
                return printTimeLeaderboard();
            }
            case 3 -> {
                return printTurnsLeaderboard();
            }
            case 4 -> {
                return printWinPercentageLeaderboard();
            }
            case 5 -> {
                return printWinsLeaderboard();
            }
            case 0 -> {
                return quit();
            }
            default -> {
                System.out.println("Invalid input. Please enter a number corresponding to the menu option.");

                return this;
            }
        }
    }

    private Menu printLeaderboard() {
        Optional<List<MainLeaderboardEntry>> optionalLeaderboard = leaderboardService.getMainLeaderboard();

        if (optionalLeaderboard.isEmpty()) {
            System.out.println("No leaderboard found. Please play some games to build up the leaderboard");

            return this;
        }

        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            System.out.printf("%-20s %-10s %s%n",
                    leaderboardEntry.playerName() + ":",
                    leaderboardEntry.numberOfTurns() + " turns",
                    ClockDisplayFormatter.format(leaderboardEntry.gameDuration()));
        });

        return this;
    }

    private Menu printTimeLeaderboard() {
        Optional<List<TimeLeaderboardEntry>> optionalLeaderboard = leaderboardService.getTimeLeaderboard();

        if (optionalLeaderboard.isEmpty()) {
            System.out.println("No leaderboard found. Please play some games to build the leaderboard");

            return this;
        }

        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            System.out.println(leaderboardEntry.playerName() + ": "
                    + ClockDisplayFormatter.format(leaderboardEntry.gameDuration()));
        });

        return this;
    }

    private Menu printTurnsLeaderboard() {
        Optional<List<TurnsLeaderboardEntry>> optionalLeaderboard = leaderboardService.getTurnsLeaderboard();

        if (optionalLeaderboard.isEmpty()) {
            System.out.println("No leaderboard found. Please play some games to build the leaderboard");

            return this;
        }

        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            System.out.println(leaderboardEntry.playerName() + ": "
                    + leaderboardEntry.numberOfTurns() + " turns");
        });

        return this;
    }

    private Menu printWinPercentageLeaderboard() {
        Optional<List<WinPercentageLeaderboardEntry>> optionalLeaderboard =
                leaderboardService.getWinPercentageLeaderboard(10);

        if (optionalLeaderboard.isEmpty()) {
            System.out.println("No leaderboard found. Please play some games to build the leaderboard");

            return this;
        }

        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            System.out.println(leaderboardEntry.playerName() + ": "
                    + leaderboardEntry.winPercentage() + "% "
                    + leaderboardEntry.gamesPlayed() + " games");
        });

        return this;
    }

    private Menu printWinsLeaderboard() {
        Optional<List<WinsLeaderboardEntry>> optionalLeaderboard =
                leaderboardService.getWinsLeaderboard();

        if (optionalLeaderboard.isEmpty()) {
            System.out.println("No leaderboard found. Please play some games to build the leaderboard");

            return this;
        }

        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            System.out.println(leaderboardEntry.playerName() + ": " + leaderboardEntry.numberOfWins());
        });

        return this;
    }

    private Menu quit() {
        return new MainMenu(appContext);
    }
}
