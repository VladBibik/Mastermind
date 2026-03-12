package dev.bibikvlad.mastermind.localization.messages.menu.main.stats;

import java.util.ResourceBundle;

public class ConsoleStatsMessages implements StatsMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleStatsMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }


    @Override
    public String getHeader() {
        return resourceBundle.getString("header_message");
    }

    @Override
    public String getGamesPlayed() {
        return resourceBundle.getString("games_played");
    }

    @Override
    public String getWins() {
        return resourceBundle.getString("wins");
    }

    @Override
    public String getWinPercentage() {
        return resourceBundle.getString("win_percentage");
    }

    @Override
    public String getTotalPlayTime() {
        return resourceBundle.getString("total_playtime");
    }

    @Override
    public String getAverageGameDuration() {
        return resourceBundle.getString("average_game_duration");
    }

    @Override
    public String getFastestWinTime() {
        return resourceBundle.getString("fastest_win_time");
    }

    @Override
    public String getBestTurnCount() {
        return resourceBundle.getString("best_turn_count");
    }
}
