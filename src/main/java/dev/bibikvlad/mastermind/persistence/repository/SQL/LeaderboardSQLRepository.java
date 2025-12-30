package dev.bibikvlad.mastermind.persistence.repository.SQL;

import dev.bibikvlad.mastermind.model.leaderboard.*;
import dev.bibikvlad.mastermind.persistence.dao.JDBC.LeaderboardJdbcDAO;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.repository.LeaderboardRepository;

import java.util.List;

public class LeaderboardSQLRepository implements LeaderboardRepository {
    private final LeaderboardJdbcDAO leaderboardJdbcDAO;
    private final TransactionManager transactionManager;

    public LeaderboardSQLRepository(LeaderboardJdbcDAO leaderboardJdbcDAO, TransactionManager transactionManager) {
        this.leaderboardJdbcDAO = leaderboardJdbcDAO;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<MainLeaderboardEntry> getMainLeaderboard() {
        return leaderboardJdbcDAO.getMainLeaderboard();
    }

    @Override
    public List<TimeLeaderboardEntry> getTimeLeaderboard() {
        return leaderboardJdbcDAO.getTimeLeaderboard();
    }

    @Override
    public List<TurnsLeaderboardEntry> getTurnsLeaderboard() {
        return List.of();
    }

    @Override
    public List<WinPercentageLeaderboardEntry> getWinRateLeaderboard() {
        return List.of();
    }

    @Override
    public List<WinsLeaderboardEntry> getWinsLeaderboard() {
        return List.of();
    }
}
