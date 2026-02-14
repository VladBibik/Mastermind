package dev.bibikvlad.mastermind.localization.messages.menu.stats;

import dev.bibikvlad.mastermind.persistence.player.model.PlayerStatistics;
import dev.bibikvlad.mastermind.values.Time;

public interface StatsMessages {
    String getDefaultStatArrangement(String playerName, PlayerStatistics playerStatistics);

    String getHeader(String playerName);

    String getGamesPlayed(long gamesPlayed);

    String getWins(long winCount);

    String getWinPercentage(double winPercentage);

    String getTotalPlayTime(Time totalPlayTime);

    String getAverageGameDuration(Time averageGameDuration);

    String getFastestWinTime(Time fastestWinTime);

    String getBestTurnCount(int minTurnWin);
}
