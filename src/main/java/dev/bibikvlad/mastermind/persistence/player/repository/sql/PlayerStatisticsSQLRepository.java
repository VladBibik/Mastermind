package dev.bibikvlad.mastermind.persistence.player.repository.sql;

import dev.bibikvlad.mastermind.persistence.player.model.PlayerStatistics;
import dev.bibikvlad.mastermind.persistence.player.repository.PlayerStatisticsRepository;
import dev.bibikvlad.mastermind.values.Time;

public class PlayerStatisticsSQLRepository implements PlayerStatisticsRepository {
    @Override
    public PlayerStatistics getPlayerStatistics(long playerId) {
        return null;
    }

    @Override
    public Time getTotalPlayTime(long playerId) {
        return null;
    }

    @Override
    public double getWinPercentage(long playerId) {
        return 0;
    }

    @Override
    public Time getAverageGameDuration(long playerId) {
        return null;
    }

    @Override
    public Time getFastestWinTime(long playerId) {
        return null;
    }

    @Override
    public long getWinCount(long playerId) {
        return 0;
    }

    @Override
    public int getMinTurnsWin(long playerId) {
        return 0;
    }
}
