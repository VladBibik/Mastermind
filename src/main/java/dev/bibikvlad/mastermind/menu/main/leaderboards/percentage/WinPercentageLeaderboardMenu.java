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
import dev.bibikvlad.mastermind.menu.main.leaderboards.printer.Column;
import dev.bibikvlad.mastermind.menu.main.leaderboards.printer.TablePrinter;
import dev.bibikvlad.mastermind.model.leaderboard.WinPercentageLeaderboardEntry;
import dev.bibikvlad.mastermind.services.LeaderboardService;

import java.util.List;
import java.util.Optional;

public class WinPercentageLeaderboardMenu extends Menu {
    private static final int DEFAULT_CUTOFF = 10;

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
            displayMenu();
            showMenuOnNextLoop = false;
        }

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

        return selection
                .map(this::handleSelection)
                .orElseGet(() -> new LeaderboardMenu(appContext));
    }

    private void displayMenu() {
        printer.printMessage(leaderboardMessages.getPercentageMenuOptions(playedGames));
    }

    private Menu handleSelection(int input) {
        if (input == 1) {
            return printWinPercentageLeaderboard(DEFAULT_CUTOFF);
        }

        if (input < DEFAULT_CUTOFF) {
            printer.printMessage(interactionMessages.getInvalidInputMessage());

            return this;
        }

        if (input > playedGames) {
            printer.printMessage(leaderboardMessages.getInvalidCutoffError(playedGames));

            return this;
        }

        return printWinPercentageLeaderboard(input);
    }

    private Menu printWinPercentageLeaderboard(int sampleSize) {
        Optional<List<WinPercentageLeaderboardEntry>> optionalLeaderboard =
                leaderboardService.getWinPercentageLeaderboard(sampleSize);

        if (optionalLeaderboard.isEmpty()) {
            printer.printMessage(leaderboardMessages.getInvalidCutoffError(sampleSize));

            return this;
        }

        TablePrinter<WinPercentageLeaderboardEntry> tablePrinter = new TablePrinter<>(printer);
        tablePrinter.print(
                optionalLeaderboard.get(),
                List.of(
                        new Column<>(leaderboardMessages.getHeaderName(), WinPercentageLeaderboardEntry::playerName),
                        new Column<>(leaderboardMessages.getHeaderPercentage(),
                                entry -> String.format("%.2f%%", entry.winPercentage())),
                        new Column<>(leaderboardMessages.getHeaderGames(),
                                entry -> Long.toString(entry.gamesPlayed()))
                )
        );

        waitForConfirmation();

        return this;
    }

    private void waitForConfirmation() {
        printer.printMessage(interactionMessages.getPressEnterMessage());

        showMenuOnNextLoop = true;

        parser.parseUserInput();
    }
}
