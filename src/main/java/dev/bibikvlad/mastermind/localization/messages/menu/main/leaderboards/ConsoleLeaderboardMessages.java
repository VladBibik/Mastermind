package dev.bibikvlad.mastermind.localization.messages.menu.main.leaderboards;

import java.text.MessageFormat;
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
    public String getLeaderboardMenuOptions() {
        return resourceBundle.getString("leaderboard.menu_options");
    }

    @Override
    public String getPercentageMenuOptions(int playedGames) {
        return MessageFormat.format(resourceBundle.getString("percentage.menu_options"), playedGames);
    }

    @Override
    public String getNoLeaderboardError() {
        return resourceBundle.getString("no_leaderboard_error");
    }

    @Override
    public String getNotEnoughGamesPlayedError() {
        return resourceBundle.getString("not_enough_games_played_error");
    }

    @Override
    public String getInvalidCutoffError(int playedGames) {
        return MessageFormat.format(resourceBundle.getString("invalid_cutoff_error"), playedGames);
    }
}
