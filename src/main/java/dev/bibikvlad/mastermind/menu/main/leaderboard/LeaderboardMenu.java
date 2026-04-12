package dev.bibikvlad.mastermind.menu.main.leaderboard;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.MainMenu;
import dev.bibikvlad.mastermind.model.leaderboard.*;
import dev.bibikvlad.mastermind.services.LeaderboardService;
import dev.bibikvlad.utils.formatters.ClockDisplayFormatter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class LeaderboardMenu extends Menu {
    private final static int PADDING = 10;

    private final Printer printer;
    private final Parser parser;
    private final LeaderboardService leaderboardService;

    public LeaderboardMenu(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
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

        String nameColumnHeader = "Name";
        String turnsColumnHeader = "Turns";
        String timeColumnHeader = "Time";

        int nameColumnWidth = getNameColumnWidth(
                optionalLeaderboard
                        .get()
                        .stream()
                        .map(MainLeaderboardEntry::playerName),
                nameColumnHeader.length());
        int turnsColumnWidth = turnsColumnHeader.length() + PADDING;

        String formatting = "%-" + nameColumnWidth + "s%-" + turnsColumnWidth + "s%s";

        System.out.printf(formatting, nameColumnHeader, turnsColumnHeader, timeColumnHeader);
        System.out.println();
        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            System.out.printf(formatting,
                    leaderboardEntry.playerName(),
                    leaderboardEntry.numberOfTurns(),
                    ClockDisplayFormatter.format(leaderboardEntry.gameDuration()));
            System.out.println();
        });

        return this;
    }

    private Menu printTimeLeaderboard() {
        Optional<List<TimeLeaderboardEntry>> optionalLeaderboard = leaderboardService.getTimeLeaderboard();

        if (optionalLeaderboard.isEmpty()) {
            System.out.println("No leaderboard found. Please play some games to build the leaderboard");

            return this;
        }

        String nameColumnHeader = "Name";
        String timeColumnHeader = "Time";

        int nameColumnWidth = getNameColumnWidth(
                optionalLeaderboard
                        .get()
                        .stream()
                        .map(TimeLeaderboardEntry::playerName),
                nameColumnHeader.length()
        );
        int timeColumnWidth = timeColumnHeader.length() + PADDING;

        String formatting = "%-" + nameColumnWidth + "s%-" + timeColumnWidth + "s";

        System.out.printf(formatting, nameColumnHeader, timeColumnHeader);
        System.out.println();
        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            System.out.printf(formatting,
                    leaderboardEntry.playerName(), ClockDisplayFormatter.format(leaderboardEntry.gameDuration()));
            System.out.println();
        });

        return this;
    }

    private Menu printTurnsLeaderboard() {
        Optional<List<TurnsLeaderboardEntry>> optionalLeaderboard = leaderboardService.getTurnsLeaderboard();

        if (optionalLeaderboard.isEmpty()) {
            System.out.println("No leaderboard found. Please play some games to build the leaderboard");

            return this;
        }

        String nameColumnHeader = "Name";
        String turnsColumnHeader = "Turns";

        int nameColumnWidth = getNameColumnWidth(
                optionalLeaderboard
                        .get()
                        .stream()
                        .map(TurnsLeaderboardEntry::playerName),
                nameColumnHeader.length()
        );
        int turnsColumnWidth = turnsColumnHeader.length() + PADDING;

        String formatting = "%-" + nameColumnWidth + "s%-" + turnsColumnWidth + "s";

        System.out.printf(formatting, "Name", "Turns");
        System.out.println();
        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            System.out.printf(formatting,
                    leaderboardEntry.playerName(), leaderboardEntry.numberOfTurns());
            System.out.println();
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

        int nameColumWidth = getNameColumnWidth(
                winPercentageLeaderboard
                        .stream()
                        .map(WinPercentageLeaderboardEntry::playerName),
                nameHeader.length());
        int percentageColumWidth = percentageHeader.length() + PADDING;

        String formatting = "%-" + nameColumWidth + "s%-" + percentageColumWidth + "s%s";

        System.out.printf(formatting, nameHeader, percentageHeader, gamesHeader);
        System.out.println();
        System.out.print(getDividerLine(nameColumWidth, percentageColumWidth));
        System.out.println();

        for (WinPercentageLeaderboardEntry entry : winPercentageLeaderboard) {
            String percentage = String.format("%.2f%%", entry.winPercentage());

            System.out.printf(formatting, entry.playerName(), percentage, entry.gamesPlayed());
            System.out.println();
        }

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

    private int getNameColumnWidth(Stream<String> namesStream, int headerLength) {
        int nameColumWidth = namesStream.map(String::length)
                .max(Integer::compareTo)
                .orElse(0);

        return Math.max(nameColumWidth, headerLength) + PADDING;
    }

    private String getDividerLine(int... headerLengths) {
        int headerLength = Arrays.stream(headerLengths).sum();

        return "_".repeat(headerLength);
    }
}
