package dev.bibikvlad.mastermind.app.bootstrap;

import dev.bibikvlad.mastermind.persistence.database.DatabaseContext;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.leaderboard.dao.LeaderboardDAO;
import dev.bibikvlad.mastermind.persistence.leaderboard.jdbc.LeaderboardJdbcDAO;
import dev.bibikvlad.mastermind.persistence.leaderboard.repository.LeaderboardRepository;
import dev.bibikvlad.mastermind.persistence.leaderboard.repository.sql.LeaderboardSQLRepository;
import dev.bibikvlad.mastermind.services.LeaderboardService;

import java.sql.Connection;
import java.sql.SQLException;

public class LeaderboardServiceGeneratorTEMP {
    public static LeaderboardService get() {
        try {
            Connection connection = DatabaseContext.getConnection();
            LeaderboardDAO leaderboardDAO = new LeaderboardJdbcDAO(connection);
            LeaderboardRepository leaderboardRepository = new LeaderboardSQLRepository(leaderboardDAO, new TransactionManager(connection));

            return new LeaderboardService(leaderboardRepository);
        } catch (SQLException exception) {
            throw new IllegalStateException(exception);
        }
    }
}
