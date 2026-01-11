package dev.bibikvlad.mastermind.app.bootstrap;

import dev.bibikvlad.mastermind.persistence.database.DatabaseContext;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.player.dao.PlayerStatisticsDAO;
import dev.bibikvlad.mastermind.persistence.player.dao.jdbc.PlayerStatisticsJdbcDAO;
import dev.bibikvlad.mastermind.persistence.player.repository.PlayerStatisticsRepository;
import dev.bibikvlad.mastermind.persistence.player.repository.sql.PlayerStatisticsSQLRepository;
import dev.bibikvlad.mastermind.services.PlayerStatisticsService;

import java.sql.Connection;
import java.sql.SQLException;

public class PlayerStatisticsServiceGeneratorTEMP {
    public static PlayerStatisticsService get() {
        try {
            Connection connection = DatabaseContext.getConnection();
            PlayerStatisticsDAO playerStatisticsDAO = new PlayerStatisticsJdbcDAO(connection);
            PlayerStatisticsRepository leaderboardRepository = new PlayerStatisticsSQLRepository(playerStatisticsDAO,
                    new TransactionManager(connection));

            return new PlayerStatisticsService(leaderboardRepository);
        } catch (SQLException exception) {
            throw new IllegalStateException(exception);
        }
    }
}
