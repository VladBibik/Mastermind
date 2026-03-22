package dev.bibikvlad.mastermind.menu.main.stats;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.common.TimeFormattingMessages;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.stats.StatsMessages;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.MainMenu;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.persistence.player.model.PlayerStatistics;
import dev.bibikvlad.mastermind.services.PlayerStatisticsService;
import dev.bibikvlad.utils.formatters.ClockDisplayFormatter;
import dev.bibikvlad.utils.formatters.TimeToStringFormatter;

import java.util.List;

public class StatsMenu extends Menu {
    private static final int LABEL_WIDTH = 35;

    private final PlayerStatisticsService playerStatisticsService;
    private final Player currentPlayer;
    private final StatsMessages statsMessages;
    private final InteractionMessages interactionMessages;
    private final TimeFormattingMessages timeFormattingMessages;
    private final Printer printer;
    private final Parser parser;

    public StatsMenu(AppContext appContext) {
        super(appContext);

        this.playerStatisticsService = appContext.services().getPlayerStatisticsService();
        this.currentPlayer = appContext.currentPlayer();
        this.statsMessages = appContext.localizationContext().getMessages(MessageType.STATS);
        this.interactionMessages = appContext.localizationContext().getMessages(MessageType.INTERACTION);
        this.timeFormattingMessages = appContext.localizationContext().getMessages(MessageType.TIME);
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

        buildStatsLines(stats).forEach(printer::printMessage);
    }

    private PlayerStatistics fetchPlayerStatistics(long playerId) {
        return playerStatisticsService.getPlayerStatistics(playerId)
                .orElseThrow(() -> new IllegalStateException("Statistics for the player with ID: "
                        + playerId + " not found"));
    }

    private List<String> buildStatsLines(PlayerStatistics stats) {
        return List.of(
                statsMessages.getHeader(currentPlayer.getPlayerName()),
                formatStat(statsMessages.getGamesPlayed(), stats.gameCount()),
                formatStat(statsMessages.getWins(), stats.winCount()),
                formatStat(statsMessages.getWinPercentage(), String.format("%.2f%%", stats.winPercentage())),
                formatStat(statsMessages.getTotalPlayTime(),
                        TimeToStringFormatter.format(stats.totalPlayTime(), timeFormattingMessages)),
                formatStat(statsMessages.getFastestWinTime(), ClockDisplayFormatter.format(stats.fastestWinTime())),
                formatStat(statsMessages.getAverageGameDuration(),
                        ClockDisplayFormatter.format(stats.averageGameDuration())),
                formatStat(statsMessages.getBestTurnCount(), stats.minTurnsWin())
        );
    }

    private String formatStat(String label, Object value) {
        return String.format("%-" + LABEL_WIDTH + "s %s", label, value);
    }

    private void confirmToContinue() {
        printer.printMessage(interactionMessages.getPressEnterMessage());

        parser.parseUserInput();
    }
}
