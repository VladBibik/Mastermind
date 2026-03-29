package dev.bibikvlad.mastermind.menu.main.leaderboard;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.MainMenu;
import dev.bibikvlad.mastermind.persistence.leaderboard.model.*;
import dev.bibikvlad.mastermind.services.LeaderboardService;
import dev.bibikvlad.utils.formatters.ClockDisplayFormatter;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class LeaderboardMenu extends Menu {
    private final static int PADDING = 10;

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

        System.out.printf("%-25s%-18s%s", "Name", "Turns", "Time");
        System.out.println();
        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            System.out.printf("%-26s %-13s %s%n",
                    checkAndCropName(leaderboardEntry.playerName()),
                    leaderboardEntry.numberOfTurns(),
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

        System.out.printf("%-29s%s", "Name", "Time");
        System.out.println();
        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            System.out.printf("%-26s %-13s%n", checkAndCropName(leaderboardEntry.playerName()),
                    ClockDisplayFormatter.format(leaderboardEntry.gameDuration()));
        });

        return this;
    }

    private Menu printTurnsLeaderboard() {
        Optional<List<TurnsLeaderboardEntry>> optionalLeaderboard = leaderboardService.getTurnsLeaderboard();

        if (optionalLeaderboard.isEmpty()) {
            System.out.println("No leaderboard found. Please play some games to build the leaderboard");

            return this;
        }

        System.out.printf("%-25s%s", "Name", "Turns");
        System.out.println();
        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            System.out.printf("%-26s %-13s%n", checkAndCropName(leaderboardEntry.playerName()),
                    leaderboardEntry.numberOfTurns());
        });

        return this;
    }

    private Menu printWinPercentageLeaderboard() {
        Optional<List<WinPercentageLeaderboardEntry>> optionalLeaderboard =
                leaderboardService.getWinPercentageLeaderboard(1);

        if (optionalLeaderboard.isEmpty()) {
            System.out.println("No leaderboard found. Please play some games to build the leaderboard");

            return this;
        }

        String nameHeader = "Name";
        String percentageHeader = "Win %";
        String gamesHeader = "Games";

        List<WinPercentageLeaderboardEntry> winPercentageLeaderboard = optionalLeaderboard.get();

        int longestName = winPercentageLeaderboard.stream()
                .map(entry -> checkAndCropName(entry.playerName()).length())
                .max(Integer::compareTo)
                .orElse(0);

        System.out.println("Longest Name: " + longestName);

        for (WinPercentageLeaderboardEntry entry : winPercentageLeaderboard) {
            String percentage = String.format("%.2f%%", entry.winPercentage());

            int nameWidth = calculateWidth(nameHeader.length(), checkAndCropName(entry.playerName()).length());
            int percentageWidth = calculateWidth(percentageHeader.length(), percentage.length());

            String formatting = "%-" + nameWidth + "s%-" + percentageWidth + "s%s";
            int lineWidth = nameWidth + percentageWidth + PADDING;

            System.out.printf(formatting, nameHeader, percentageHeader, gamesHeader);
            System.out.println();
            System.out.print("-".repeat(Math.max(0, lineWidth)));
            System.out.println();
            System.out.printf(formatting, checkAndCropName(entry.playerName()), percentage, entry.gamesPlayed());
        }

        return this;
    }

    private int calculateWidth(int header, int data) {
        return Math.max(header, data) + PADDING;
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

    private String checkAndCropName(String playerName) {
        int length = playerName.codePointCount(0, playerName.length());

        if (length <= 20) return playerName;

        int endIndex = playerName.offsetByCodePoints(0, 20);

        return playerName.substring(0, endIndex) + "...";
    }

    private Menu quit() {
        return new MainMenu(appContext);
    }
}
