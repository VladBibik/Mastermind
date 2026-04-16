package dev.bibikvlad.mastermind.localization.messages.menu.main.leaderboards;

import java.util.ResourceBundle;

public class ConsoleLeaderboardMessages implements LeaderboardMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleLeaderboardMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getHeaderName() {
        return resourceBundle.getString("header.name");
    }

    @Override
    public String getHeaderTurns() {
        return resourceBundle.getString("header.turns");
    }

    @Override
    public String getHeaderTime() {
        return resourceBundle.getString("header.time");
    }

    @Override
    public String getHeaderPercentage() {
        return resourceBundle.getString("header.percentage");
    }

    @Override
    public String getHeaderGames() {
        return resourceBundle.getString("header.games");
    }

    @Override
    public String getHeaderWins() {
        return resourceBundle.getString("header.wins");
    }

    @Override
    public String getMenuOptions() {
        return resourceBundle.getString("menu_options");
    }

    @Override
    public String getNoLeaderboardError() {
        return resourceBundle.getString("no_leaderboard_error");
    }
}
