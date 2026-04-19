package dev.bibikvlad.mastermind.localization.messages.menu.main.leaderboards;

import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;

public interface LeaderboardMessages extends LocalizedMessages {
    String getHeaderName();

    String getHeaderTurns();

    String getHeaderTime();

    String getHeaderPercentage();

    String getHeaderGames();

    String getHeaderWins();

    String getLeaderboardMenuOptions();

    String getPercentageMenuOptions();

    String getNoLeaderboardError();

    String getNotEnoughGamesPlayedError();
}
