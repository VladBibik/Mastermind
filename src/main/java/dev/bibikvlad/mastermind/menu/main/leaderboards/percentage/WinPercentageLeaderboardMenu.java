package dev.bibikvlad.mastermind.menu.main.leaderboards.percentage;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.AnsiSafeFormatter;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.app.printer.table.Column;
import dev.bibikvlad.mastermind.app.printer.table.TablePrinter;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.leaderboards.LeaderboardMessages;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.leaderboards.LeaderboardMenu;
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

    private boolean shouldRenderMenu = true;

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
        if (shouldRenderMenu) {
            displayMenu();
            shouldRenderMenu = false;
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
            printer.printMessage(interactionMessages.getInvalidInput());

            return this;
        }

        if (input > playedGames) {
            printer.printMessage(leaderboardMessages.getInvalidCutoffError(playedGames));

            return this;
        }

        return printWinPercentageLeaderboard(input);
    }

    /*
    The player name column may contain ANSI color codes.

    TablePrinter calculates column widths using String.length(), so the
    ANSI reset sequence added by AnsiSafeFormatter contributes to the string
    length even though it is not visible when rendered. If only the player
    names are isolated, the first column becomes wider than its header and
    table alignment breaks.

    To keep width calculations consistent, the corresponding header must
    also be wrapped with AnsiSafeFormatter.
     */
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
                        new Column<>(AnsiSafeFormatter.isolate(leaderboardMessages.getHeaderName()),
                                entry -> AnsiSafeFormatter.isolate(entry.playerName())),
                        new Column<>(leaderboardMessages.getHeaderPercentage(),
                                entry -> String.format("%.2f%%", entry.winPercentage())),
                        new Column<>(leaderboardMessages.getHeaderGames(),
                                entry -> String.valueOf(entry.gamesPlayed()))
                )
        );

        waitForConfirmation();

        return this;
    }

    private void waitForConfirmation() {
        printer.printMessage(interactionMessages.getPressEnter());

        shouldRenderMenu = true;

        parser.parseUserInput();
    }
}
