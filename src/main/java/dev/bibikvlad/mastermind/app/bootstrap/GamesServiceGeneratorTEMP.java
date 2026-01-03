package dev.bibikvlad.mastermind.app.bootstrap;

import dev.bibikvlad.mastermind.persistence.game.dao.GamesDAO;
import dev.bibikvlad.mastermind.persistence.game.jdbc.GamesJdbcDAO;
import dev.bibikvlad.mastermind.persistence.database.DatabaseContext;
import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.repository.GamesRepository;
import dev.bibikvlad.mastermind.persistence.repository.SQL.GamesSQLRepository;
import dev.bibikvlad.mastermind.services.GamesService;

import java.sql.Connection;
import java.sql.SQLException;

public class GamesServiceGeneratorTEMP {
    public static GamesService get() {
        try {
            Connection connection = DatabaseContext.getConnection();
            GamesDAO gamesDAO = new GamesJdbcDAO(connection);
            GamesRepository gamesRepository = new GamesSQLRepository(gamesDAO, new TransactionManager(connection));

            return new GamesService(gamesRepository);
        } catch (SQLException exception) {
            throw new IllegalStateException(exception);
        }
    }
}
