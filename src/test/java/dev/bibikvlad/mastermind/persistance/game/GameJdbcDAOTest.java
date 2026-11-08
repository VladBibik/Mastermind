package dev.bibikvlad.mastermind.persistance.game;

import dev.bibikvlad.mastermind.persistence.game.dao.jdbc.GameJdbcDAO;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GameJdbcDAOTest {
    private Connection connection;
    private GameJdbcDAO gameJdbcDAO;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");

        try (Statement statement = connection.createStatement()) {
            statement.execute("""
                    CREATE TABLE games (
                        game_id INTEGER PRIMARY KEY AUTOINCREMENT,
                        player_id INTEGER NOT NULL,
                        duration_milliseconds INTEGER NOT NULL,
                        result TEXT NOT NULL,
                        number_of_turns INTEGER NOT NULL,
                        started_at TEXT DEFAULT CURRENT_TIMESTAMP
                    )
                    """);
        }
    }
}
