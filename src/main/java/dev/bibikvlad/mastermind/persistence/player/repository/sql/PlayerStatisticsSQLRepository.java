package dev.bibikvlad.mastermind.persistence.player.repository.sql;

import dev.bibikvlad.mastermind.persistence.player.dao.PlayerStatisticsDAO;
import dev.bibikvlad.mastermind.persistence.player.model.PlayerStatistics;
import dev.bibikvlad.mastermind.persistence.player.repository.PlayerStatisticsRepository;
import dev.bibikvlad.mastermind.values.Time;

public class PlayerStatisticsSQLRepository implements PlayerStatisticsRepository {
    private final PlayerStatisticsDAO  playerStatisticsDAO;

    public PlayerStatisticsSQLRepository(PlayerStatisticsDAO playerStatisticsDAO) {
        this.playerStatisticsDAO = playerStatisticsDAO;
    }

    @Override
    public PlayerStatistics getPlayerStatistics(long playerId) {
        return playerStatisticsDAO.getPlayerStatistics(playerId);
    }

    @Override
    public Time getTotalPlayTime(long playerId) {
        return playerStatisticsDAO.getTotalPlayTime(playerId);
    }

    @Override
    public double getWinPercentage(long playerId) {
        return playerStatisticsDAO.getWinPercentage(playerId);
    }

    @Override
    public Time getAverageGameDuration(long playerId) {
        return playerStatisticsDAO.getAverageGameDuration(playerId);
    }

    @Override
    public Time getFastestWinTime(long playerId) {
        return playerStatisticsDAO.getFastestWinTime(playerId);
    }

    @Override
    public long getWinCount(long playerId) {
        return playerStatisticsDAO.getWinCount(playerId);
    }

    @Override
    public int getMinTurnsWin(long playerId) {
        return playerStatisticsDAO.getMinTurnsWin(playerId);
    }
}
