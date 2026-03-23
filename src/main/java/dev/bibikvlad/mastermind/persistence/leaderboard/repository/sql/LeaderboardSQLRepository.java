package dev.bibikvlad.mastermind.persistence.leaderboard.repository.sql;

import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.leaderboard.dao.LeaderboardDAO;
import dev.bibikvlad.mastermind.persistence.leaderboard.jdbc.LeaderboardJdbcDAO;
import dev.bibikvlad.mastermind.persistence.leaderboard.model.*;
import dev.bibikvlad.mastermind.persistence.leaderboard.repository.LeaderboardRepository;

import java.util.List;

public class LeaderboardSQLRepository implements LeaderboardRepository {
    private final LeaderboardDAO leaderboardJdbcDAO;
    private final TransactionManager transactionManager;

    public LeaderboardSQLRepository(LeaderboardDAO leaderboardJdbcDAO, TransactionManager transactionManager) {
        this.leaderboardJdbcDAO = leaderboardJdbcDAO;
        this.transactionManager = transactionManager;
    }

    @Override
    public List<MainLeaderboardEntry> getOverallLeaderboard() {
        return leaderboardJdbcDAO.getOverallLeaderboard();
    }

    @Override
    public List<TimeLeaderboardEntry> getTimeLeaderboard() {
        return leaderboardJdbcDAO.getTimeLeaderboard();
    }

    @Override
    public List<TurnsLeaderboardEntry> getTurnsLeaderboard() {
        return leaderboardJdbcDAO.getTurnsLeaderboard();
    }

    @Override
    public List<WinPercentageLeaderboardEntry> getWinPercentageLeaderboard(int minGamesPlayed) {
        return leaderboardJdbcDAO.getWinPercentageLeaderboard(minGamesPlayed);
    }

    @Override
    public List<WinsLeaderboardEntry> getWinsLeaderboard() {
        return leaderboardJdbcDAO.getWinsLeaderboard();
    }
}
