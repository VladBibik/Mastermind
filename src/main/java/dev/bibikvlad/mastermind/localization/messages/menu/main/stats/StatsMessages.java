package dev.bibikvlad.mastermind.localization.messages.menu.main.stats;

import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;

public interface StatsMessages extends LocalizedMessages {
    String getHeader(String playerName);

    String getGamesPlayed();

    String getWins();

    String getWinPercentage();

    String getTotalPlayTime();

    String getAverageGameDuration();

    String getFastestWinTime();

    String getBestTurnCount();
}
