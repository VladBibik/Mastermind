package dev.bibikvlad.mastermind.localization.messages.menu.stats;

import dev.bibikvlad.mastermind.persistence.player.model.PlayerStatistics;
import dev.bibikvlad.mastermind.values.Time;
import dev.bibikvlad.utils.formatters.ClockDisplayFormatter;
import dev.bibikvlad.utils.formatters.TimeToStringFormatter;

import java.util.ResourceBundle;

public class ConsoleStatsMessages implements StatsMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleStatsMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getDefaultStatArrangement(String playerName, PlayerStatistics playerStatistics) {
        StringBuilder playerStatsMessageBuilder = new StringBuilder();

        playerStatsMessageBuilder
                .append(getHeader(playerName)).append("\n")
                .append(getGamesPlayed(playerStatistics.gameCount())).append("\n")
                .append(getWins(playerStatistics.winCount())).append("\n")
                .append(getWinPercentage(playerStatistics.winPercentage())).append("\n")
                .append(getTotalPlayTime(playerStatistics.totalPlayTime())).append("\n")
                .append(getAverageGameDuration(playerStatistics.averageGameDuration())).append("\n")
                .append(getFastestWinTime(playerStatistics.fastestWinTime())).append("\n")
                .append(getBestTurnCount(playerStatistics.minTurnsWin())).append("\n");

        return playerStatsMessageBuilder.toString();
    }

    @Override
    public String getHeader(String playerName) {
        return resourceBundle.getString("header_message")
                .replace("{PLAYER_NAME}", playerName);
    }

    @Override
    public String getGamesPlayed(long gamesPlayed) {
        return resourceBundle.getString("games_played")
                .replace("{GAME_COUNT}", String.valueOf(gamesPlayed));
    }

    @Override
    public String getWins(long winCount) {
        return resourceBundle.getString("wins").replace("{WIN_COUNT}", String.valueOf(winCount));
    }

    @Override
    public String getWinPercentage(double winPercentage) {
        return resourceBundle.getString("win_percentage")
                .replace("{WIN_PERCENTAGE}", String.valueOf(winPercentage));
    }

    @Override
    public String getTotalPlayTime(Time totalPlayTime) {
        return resourceBundle.getString("total_playtime")
                .replace("{TOTAL_PLAYTIME}", TimeToStringFormatter.format(totalPlayTime));
    }

    @Override
    public String getAverageGameDuration(long averageGameDuration) {
        return resourceBundle.getString("average_game_duration")
                .replace("{AVERAGE_GAME_DURATION}", ClockDisplayFormatter.format(averageGameDuration));
    }

    @Override
    public String getFastestWinTime(long fastestWinTime) {
        return resourceBundle.getString("fastest_win_time")
                .replace("{FASTEST_WIN_TIME}", ClockDisplayFormatter.format(fastestWinTime));
    }

    @Override
    public String getBestTurnCount(int minTurnsWin) {
        return resourceBundle.getString("best_turn_count")
                .replace("{MIN_TURNS_WIN}", String.valueOf(minTurnsWin));
    }
}
