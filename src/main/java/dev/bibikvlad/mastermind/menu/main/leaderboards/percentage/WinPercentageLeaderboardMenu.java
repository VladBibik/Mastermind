package dev.bibikvlad.mastermind.menu.main.leaderboards.percentage;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.leaderboards.LeaderboardMessages;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.leaderboards.LeaderboardMenu;
import dev.bibikvlad.mastermind.model.leaderboard.WinPercentageLeaderboardEntry;
import dev.bibikvlad.mastermind.services.LeaderboardService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class WinPercentageLeaderboardMenu extends Menu {
    private final Printer printer;
    private final Parser parser;
    private final LeaderboardMessages leaderboardMessages;
    private final InteractionMessages interactionMessages;
    private final LeaderboardService leaderboardService;
    private final int playedGames;

    private boolean showMenuOnNextLoop = true;

    public WinPercentageLeaderboardMenu(AppContext appContext, int playedGames) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.leaderboardMessages = appContext.localizationContext().getMessages(MessageType.LEADERBOARDS);
        this.interactionMessages = appContext.localizationContext().getMessages(MessageType.INTERACTION);
        this.leaderboardService = appContext.services().getLeaderboardService();
        this.playedGames = playedGames;
    }

    @Override
    public Menu run() {
        if (showMenuOnNextLoop) {
            displayMenu(playedGames);
            showMenuOnNextLoop = false;
        }

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(appContext.parser());

        return selection
                .map(this::validateCutoff)
                .orElseGet(() -> new LeaderboardMenu(appContext));
    }

    private void displayMenu(int playedGames) {
        printer.printMessage(leaderboardMessages.getPercentageMenuOptions(playedGames));
    }

    private Menu validateCutoff(int userInputNumber) {
        if (userInputNumber >= 10 && userInputNumber <= playedGames) {
            return printWinPercentageLeaderboard(userInputNumber);
        } else {
            return checkIfInputBiggerThanMaxGamesPlayed(userInputNumber);
        }
    }

    //TODO: Rename to something comprehensible
    private Menu checkIfInputBiggerThanMaxGamesPlayed(int userInputNumber) {
        if (userInputNumber > playedGames) {
            printer.printMessage(leaderboardMessages.getInvalidCutoffError(playedGames));

            return this;
        } else {
            return shortcutOptionSwitcher(userInputNumber);
        }
    }

    private Menu shortcutOptionSwitcher(int userInputNumber) {
        final int DEFAULT_CUTOFF_VALUE = 10;

        if (userInputNumber == 1) {
            return printWinPercentageLeaderboard(DEFAULT_CUTOFF_VALUE);
        } else {
            printer.printMessage(interactionMessages.getInvalidInputMessage());

            return this;
        }
    }

    private Menu printWinPercentageLeaderboard(int sampleSize) {
        Optional<List<WinPercentageLeaderboardEntry>> optionalLeaderboard =
                leaderboardService.getWinPercentageLeaderboard(sampleSize);

        if (optionalLeaderboard.isEmpty()) {
            printer.printMessage(leaderboardMessages.getInvalidCutoffError(sampleSize));

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

        showMenuOnNextLoop = true;

        parser.parseUserInput();
    }
}
