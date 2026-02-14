package dev.bibikvlad.mastermind.localization.messages.menu.stats;

import dev.bibikvlad.mastermind.persistence.player.model.PlayerStatistics;
import dev.bibikvlad.mastermind.values.Time;

import java.util.ResourceBundle;

public class ConsoleStatsMessages implements StatsMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleStatsMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getDefaultStatArrangement(String playerName, PlayerStatistics playerStatistics) {
        return getHeader(playerName);
    }

    @Override
    public String getHeader(String playerName) {
        return resourceBundle.getString("header_message")
                .replace("{PLAYER_NAME}", playerName);
    }

    @Override
    public String getGamesPlayed(long gamesPlayed) {
        return "";
    }

    @Override
    public String getWins(long winCount) {
        return "";
    }

    @Override
    public String getWinPercentage(double winPercentage) {
        return "";
    }

    @Override
    public String getTotalPlayTime(Time totalPlayTime) {
        return "";
    }

    @Override
    public String getAverageGameDuration(Time averageGameDuration) {
        return "";
    }

    @Override
    public String getFastestWinTime(Time fastestWinTime) {
        return "";
    }

    @Override
    public String getBestTurnCount(int minTurnWin) {
        return "";
    }
}
