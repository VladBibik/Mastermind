package dev.bibikvlad.mastermind.menu.main.stats;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.AnsiSafeFormatter;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.common.TimeFormattingMessages;
import dev.bibikvlad.mastermind.localization.messages.error.ErrorMessages;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.stats.StatsMessages;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.MainMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.model.player.PlayerStatistics;
import dev.bibikvlad.mastermind.services.PlayerStatisticsService;
import dev.bibikvlad.utils.formatters.ClockDisplayFormatter;
import dev.bibikvlad.utils.formatters.TimeToStringFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StatsMenu extends Menu {
    private static final int PADDING = 10;

    private final PlayerStatisticsService playerStatisticsService;
    private final Player currentPlayer;
    private final StatsMessages statsMessages;
    private final InteractionMessages interactionMessages;
    private final TimeFormattingMessages timeFormattingMessages;
    private final ErrorMessages errorMessages;
    private final Printer printer;
    private final Parser parser;

    public StatsMenu(AppContext appContext) {
        super(appContext);

        this.playerStatisticsService = appContext.services().getPlayerStatisticsService();
        this.currentPlayer = appContext.currentPlayer();
        this.statsMessages = appContext.localizationContext().getMessages(MessageType.STATS);
        this.interactionMessages = appContext.localizationContext().getMessages(MessageType.INTERACTION);
        this.timeFormattingMessages = appContext.localizationContext().getMessages(MessageType.TIME);
        this.errorMessages = appContext.localizationContext().getMessages(MessageType.ERROR);
        this.printer = appContext.printer();
        this.parser = appContext.parser();
    }

    @Override
    public Menu run() {
        printStats();

        confirmToContinue();

        return new MainMenu(appContext);
    }

    private void printStats() {
        PlayerStatistics stats = fetchPlayerStatistics(currentPlayer.getId());

        List<StatRow> statsRows = createStatsLines(stats);

        RowWidths longestRowWidths = findLongestWidths(statsRows);

        String formatting = createFormattingString(longestRowWidths);

        printer.printMessage(statsMessages.getHeader(AnsiSafeFormatter.isolate(currentPlayer.getPlayerName())));

        printDividingLine(longestRowWidths);

        printStatsLines(statsRows, formatting);
    }

    private PlayerStatistics fetchPlayerStatistics(long playerId) {
        return playerStatisticsService.getPlayerStatistics(playerId)
                .orElseThrow(() -> new IllegalStateException(errorMessages.getStatsNotFoundMessage(playerId)));
    }

    private List<StatRow> createStatsLines(PlayerStatistics stats) {
        Locale currentLocale = currentPlayer.getPlayerConfig().locale().getLocale();

        List<StatRow> lines = new ArrayList<>();
        lines.add(new StatRow(statsMessages.getGamesPlayed(), String.valueOf(stats.gameCount())));
        lines.add(new StatRow(statsMessages.getWins(), String.valueOf(stats.winCount())));
        lines.add(new StatRow(statsMessages.getWinPercentage(),
                String.format(currentLocale, "%.2f%%", stats.winPercentage())));
        lines.add(new StatRow(statsMessages.getTotalPlayTime(),
                TimeToStringFormatter.format(stats.totalPlayTime(), timeFormattingMessages)));
        lines.add(new StatRow(statsMessages.getFastestWinTime(), ClockDisplayFormatter.format(stats.fastestWinTime())));
        lines.add(new StatRow(statsMessages.getAverageGameDuration(),
                ClockDisplayFormatter.format(stats.averageGameDuration())));
        lines.add(new StatRow(statsMessages.getBestTurnCount(), String.valueOf(stats.minTurnsWin())));

        return lines;
    }

    private RowWidths findLongestWidths(List<StatRow> statsRows) {
        return new RowWidths(
                statsRows
                        .stream()
                        .mapToInt(entry -> entry.label().length())
                        .max()
                        .orElse(0),
                statsRows
                        .stream()
                        .mapToInt(entry -> entry.value().length())
                        .max()
                        .orElse(0));
    }

    private String createFormattingString(RowWidths longestRowWidths) {
        return "%-" + (longestRowWidths.label() + PADDING) + "s%s";
    }

    private void printDividingLine(RowWidths longestRowWidths) {
        printer.printMessage("-".repeat(longestRowWidths.label() + longestRowWidths.value() + (PADDING * 2)));
    }

    private void printStatsLines(List<StatRow> statRows, String formatting) {
        statRows.forEach(entry -> {
            String statLine = String.format(formatting, entry.label(), entry.value());

            printer.printMessage(statLine);
        });
    }

    private void confirmToContinue() {
        printer.printMessage(interactionMessages.getPressEnterMessage());

        parser.parseUserInput();
    }
}
