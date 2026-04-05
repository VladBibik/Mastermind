package dev.bibikvlad.mastermind.persistence.player.repository.sql;

import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.player.dao.PlayerStatisticsDAO;
import dev.bibikvlad.mastermind.model.player.PlayerStatistics;
import dev.bibikvlad.mastermind.persistence.player.repository.PlayerStatisticsRepository;
import dev.bibikvlad.mastermind.values.Time;

public class PlayerStatisticsSQLRepository implements PlayerStatisticsRepository {
    private final PlayerStatisticsDAO  playerStatisticsDAO;
    private final TransactionManager transactionManager;

    public PlayerStatisticsSQLRepository(PlayerStatisticsDAO playerStatisticsDAO,
                                         TransactionManager transactionManager) {
        this.playerStatisticsDAO = playerStatisticsDAO;
        this.transactionManager = transactionManager;
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
    public long getAverageGameDuration(long playerId) {
        return playerStatisticsDAO.getAverageGameDuration(playerId);
    }

    @Override
    public long getFastestWinTime(long playerId) {
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
