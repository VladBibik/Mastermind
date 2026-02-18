package dev.bibikvlad.mastermind.persistence.player.repository;

import dev.bibikvlad.mastermind.persistence.player.model.PlayerStatistics;
import dev.bibikvlad.mastermind.values.Time;

public interface PlayerStatisticsRepository {
    PlayerStatistics getPlayerStatistics(long playerId);

    Time getTotalPlayTime(long playerId);

    double getWinPercentage(long playerId);

    long getAverageGameDuration(long playerId);

    long getFastestWinTime(long playerId);

    long getWinCount(long playerId);

    int getMinTurnsWin(long playerId);
}
