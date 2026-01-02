package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.model.player.PlayerStatistic;
import dev.bibikvlad.mastermind.values.Time;

public interface PlayerStatisticsDAO {
    PlayerStatistic getPlayerStatistic(int playerId);

    Time getTotalPlayTime(long playerId);

    double getWinPercentage(long playerId);

    Time getAverageGameDuration(long playerId);

    Time getFastestWinTime(long playerId);

    long getWinCount(long playerId);

    int getMinTurnsWin(long playerId);
}
