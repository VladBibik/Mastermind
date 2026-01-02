package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.model.player.PlayerStatistics;
import dev.bibikvlad.mastermind.values.Time;

public interface PlayerStatisticsDAO {
    PlayerStatistics getPlayerStatistics(int playerId);

    Time getTotalPlayTime(long playerId);

    double getWinPercentage(long playerId);

    Time getAverageGameDuration(long playerId);

    Time getFastestWinTime(long playerId);

    long getWinCount(long playerId);

    int getMinTurnsWin(long playerId);
}
