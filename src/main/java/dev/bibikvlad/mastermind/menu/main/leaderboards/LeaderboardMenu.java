package dev.bibikvlad.mastermind.menu.main.leaderboards;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.leaderboards.LeaderboardMessages;
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
    private final Printer printer;
    private final Parser parser;
    private final LeaderboardMessages leaderboardMessages;
    private final InteractionMessages interactionMessages;
    private final LeaderboardService leaderboardService;

    public LeaderboardMenu(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.leaderboardMessages = appContext.localizationContext().getMessages(MessageType.LEADERBOARDS);
        this.interactionMessages = appContext.localizationContext().getMessages(MessageType.INTERACTION);
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
        printer.printMessage(leaderboardMessages.getMenuOptions());
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
                printer.printMessage(interactionMessages.getInvalidInputMessage());

                return this;
            }
        }
    }

    private Menu printLeaderboard() {
        Optional<List<MainLeaderboardEntry>> optionalLeaderboard = leaderboardService.getMainLeaderboard();

        if (optionalLeaderboard.isEmpty()) {
            printer.printMessage(leaderboardMessages.getNoLeaderboardError());

            return this;
        }

        String nameColumnHeader = leaderboardMessages.getHeaderName();
        String turnsColumnHeader = leaderboardMessages.getHeaderTurns();
        String timeColumnHeader = leaderboardMessages.getHeaderTime();

        int nameColumnWidth = getNameColumnWidth(
                optionalLeaderboard
                        .get()
                        .stream()
                        .map(MainLeaderboardEntry::playerName),
                nameColumnHeader.length());
        int turnsColumnWidth = addPadding(turnsColumnHeader.length());
        int timeColumnWidth = addPadding(timeColumnHeader.length());

        String formatting = "%-" + nameColumnWidth + "s%-" + turnsColumnWidth + "s%s";
        String header = String.format(formatting, nameColumnHeader, turnsColumnHeader, timeColumnHeader);

        printer.printMessage(header);
        printDividerLine(nameColumnWidth, turnsColumnWidth, timeColumnWidth);

        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            String row = String.format(formatting,
                    leaderboardEntry.playerName(),
                    leaderboardEntry.numberOfTurns(),
                    ClockDisplayFormatter.format(leaderboardEntry.gameDuration()));

            printer.printMessage(row);
        });

        waitForConfirmation();

        return this;
    }

    private Menu printTimeLeaderboard() {
        Optional<List<TimeLeaderboardEntry>> optionalLeaderboard = leaderboardService.getTimeLeaderboard();

        if (optionalLeaderboard.isEmpty()) {
            printer.printMessage(leaderboardMessages.getNoLeaderboardError());

            return this;
        }

        String nameColumnHeader = leaderboardMessages.getHeaderName();
        String timeColumnHeader = leaderboardMessages.getHeaderTime();

        int nameColumnWidth = getNameColumnWidth(
                optionalLeaderboard
                        .get()
                        .stream()
                        .map(TimeLeaderboardEntry::playerName),
                nameColumnHeader.length()
        );
        int timeColumnWidth = addPadding(timeColumnHeader.length());

        String formatting = "%-" + nameColumnWidth + "s%-" + timeColumnWidth + "s";
        String header = String.format(formatting, nameColumnHeader, timeColumnHeader);

        printer.printMessage(header);
        printDividerLine(nameColumnWidth, timeColumnWidth);

        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            String row = String.format(formatting,
                    leaderboardEntry.playerName(),
                    ClockDisplayFormatter.format(leaderboardEntry.gameDuration()));

            printer.printMessage(row);
        });

        waitForConfirmation();

        return this;
    }

    private Menu printTurnsLeaderboard() {
        Optional<List<TurnsLeaderboardEntry>> optionalLeaderboard = leaderboardService.getTurnsLeaderboard();

        if (optionalLeaderboard.isEmpty()) {
            printer.printMessage(leaderboardMessages.getNoLeaderboardError());

            return this;
        }

        String nameColumnHeader = leaderboardMessages.getHeaderName();
        String turnsColumnHeader = leaderboardMessages.getHeaderTurns();

        int nameColumnWidth = getNameColumnWidth(
                optionalLeaderboard
                        .get()
                        .stream()
                        .map(TurnsLeaderboardEntry::playerName),
                nameColumnHeader.length()
        );
        int turnsColumnWidth = addPadding(turnsColumnHeader.length());

        String formatting = "%-" + nameColumnWidth + "s%-" + turnsColumnWidth + "s";
        String header = String.format(formatting, nameColumnHeader, turnsColumnHeader);

        printer.printMessage(header);
        printDividerLine(nameColumnWidth, turnsColumnWidth);

        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            String row = String.format(formatting,
                    leaderboardEntry.playerName(),
                    leaderboardEntry.numberOfTurns());

            printer.printMessage(row);
        });

        waitForConfirmation();

        return this;
    }

    private Menu printWinPercentageLeaderboard() {
        Optional<List<WinPercentageLeaderboardEntry>> optionalLeaderboard =
                leaderboardService.getWinPercentageLeaderboard(1);

        if (optionalLeaderboard.isEmpty()) {
            printer.printMessage(leaderboardMessages.getNoLeaderboardError());

            return this;
        }

        String nameHeader = leaderboardMessages.getHeaderName();
        String percentageHeader = leaderboardMessages.getHeaderPercentage();
        String gamesHeader = leaderboardMessages.getHeaderGames();

        List<WinPercentageLeaderboardEntry> winPercentageLeaderboard = optionalLeaderboard.get();

        int nameColumWidth = getNameColumnWidth(
                winPercentageLeaderboard
                        .stream()
                        .map(WinPercentageLeaderboardEntry::playerName),
                nameHeader.length());
        int percentageColumWidth = addPadding(percentageHeader.length());
        int gamesColumWidth = addPadding(gamesHeader.length());

        String formatting = "%-" + nameColumWidth + "s%-" + percentageColumWidth + "s%s";
        String header = String.format(formatting, nameHeader, percentageHeader, gamesHeader);

        printer.printMessage(header);
        printDividerLine(nameColumWidth, percentageColumWidth, gamesColumWidth);

        winPercentageLeaderboard.forEach(leaderboardEntry -> {
            String percentage = String.format("%.2f%%", leaderboardEntry.winPercentage());
            String row = String.format(formatting,
                    leaderboardEntry.playerName(),
                    percentage,
                    leaderboardEntry.gamesPlayed());

            printer.printMessage(row);
        });

        waitForConfirmation();

        return this;
    }

    private Menu printWinsLeaderboard() {
        Optional<List<WinsLeaderboardEntry>> optionalLeaderboard =
                leaderboardService.getWinsLeaderboard();

        if (optionalLeaderboard.isEmpty()) {
            printer.printMessage(leaderboardMessages.getNoLeaderboardError());

            return this;
        }

        String nameColumnHeader = leaderboardMessages.getHeaderName();
        String winsColumnHeader = leaderboardMessages.getHeaderWins();

        int nameColumnWidth = getNameColumnWidth(
                optionalLeaderboard
                        .get()
                        .stream()
                        .map(WinsLeaderboardEntry::playerName),
                nameColumnHeader.length());
        int winsColumnWidth = addPadding(winsColumnHeader.length());

        String formatting = "%-" + nameColumnWidth + "s%-" + winsColumnWidth + "s";
        String header = String.format(formatting, nameColumnHeader, winsColumnHeader);

        printer.printMessage(header);
        printDividerLine(nameColumnWidth, winsColumnWidth);

        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            String row = String.format(formatting,
                    leaderboardEntry.playerName(),
                    leaderboardEntry.numberOfWins());

            printer.printMessage(row);
        });

        waitForConfirmation();

        return this;
    }

    private Menu quit() {
        return new MainMenu(appContext);
    }

    private int getNameColumnWidth(Stream<String> namesStream, int headerLength) {
        int nameColumWidth = namesStream.map(String::length)
                .max(Integer::compareTo)
                .orElse(0);

        return addPadding(Math.max(nameColumWidth, headerLength));
    }

    private void printDividerLine(int... headerLengths) {
        int headerLength = Arrays.stream(headerLengths).sum();

        printer.printMessage("-".repeat(headerLength));
    }

    private int addPadding(int value) {
        int padding = 10;

        return value + padding;
    }

    private void waitForConfirmation() {
        printer.printMessage(interactionMessages.getPressEnterMessage());

        parser.parseUserInput();
    }
}
