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

import java.util.*;

public class StatsMenu extends Menu {
    private static final int LABEL_WIDTH = 35;
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

        Map<String, String> statsLines = createStatsLines(stats);

        List<Integer> longestValues = findLongestValues(statsLines);

        printer.printMessage(statsMessages.getHeader(AnsiSafeFormatter.isolate(currentPlayer.getPlayerName())));
    }

    private PlayerStatistics fetchPlayerStatistics(long playerId) {
        return playerStatisticsService.getPlayerStatistics(playerId)
                .orElseThrow(() -> new IllegalStateException(errorMessages.getStatsNotFoundMessage(playerId)));
    }

    private Map<String, String> createStatsLines(PlayerStatistics stats) {
        Locale currentLocale = currentPlayer.getPlayerConfig().locale().getLocale();

        Map<String, String> lines = new LinkedHashMap<>();
        lines.put(statsMessages.getGamesPlayed(), String.valueOf(stats.gameCount()));
        lines.put(statsMessages.getWins(), String.valueOf(stats.winCount()));
        lines.put(statsMessages.getWinPercentage(),
                String.format(currentLocale, "%.2f%%", stats.winPercentage()));
        lines.put(statsMessages.getTotalPlayTime(),
                TimeToStringFormatter.format(stats.totalPlayTime(), timeFormattingMessages));
        lines.put(statsMessages.getFastestWinTime(), ClockDisplayFormatter.format(stats.fastestWinTime()));
        lines.put(statsMessages.getAverageGameDuration(),
                ClockDisplayFormatter.format(stats.averageGameDuration()));
        lines.put(statsMessages.getBestTurnCount(), String.valueOf(stats.minTurnsWin()));

        return lines;
    }

    private int findLongestLabel(Map<String, String> statsLines) {
        return statsLines
                .keySet()
                .stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    private int findLongestStat(Map<String, String> statsLines) {
        return statsLines
                .values()
                .stream()
                .mapToInt(String::length)
                .max()
                .orElse(0);
    }

    private String createFormattingString(int longestLabelLength, int longestStatLength) {
        return "%-" + longestLabelLength + PADDING + "s%-" + longestStatLength + PADDING + "s";
    }

    private void printDividingLine(int longestLabelLength, int longestStatLength) {
        printer.printMessage("-".repeat(longestLabelLength + longestStatLength + (PADDING * 2)));
    }

    private void printStatsLines(Map<String, String> statsLines, String formatting) {
        statsLines.forEach((key, value) -> {
            String statLine = String.format(formatting, key, value);

            printer.printMessage(statLine);
        });
    }

    private void confirmToContinue() {
        printer.printMessage(interactionMessages.getPressEnterMessage());

        parser.parseUserInput();
    }
}
