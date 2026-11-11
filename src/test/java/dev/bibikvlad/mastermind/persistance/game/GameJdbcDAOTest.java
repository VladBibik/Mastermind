package dev.bibikvlad.mastermind.persistance.game;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.game.data.GameOutcome;
import dev.bibikvlad.mastermind.game.data.GameResult;
import dev.bibikvlad.mastermind.model.game.Game;
import dev.bibikvlad.mastermind.persistence.game.dao.jdbc.GameJdbcDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

        gameJdbcDAO = new GameJdbcDAO(connection);
    }

    @Test
    @DisplayName("Should return an empty list when no games exist")
    void findAllNoGamesReturnsEmptyList() {
        List<Game> games = gameJdbcDAO.findAll();

        assertNotNull(games);
        assertTrue(games.isEmpty());
    }

    @Test
    @DisplayName("Should return the correct number of games")
    void countMultipleGamesReturnsCorrectCount() {
        insert(1L);
        insert(2L);
        insert(3L);
        insert(4L);

        int count = gameJdbcDAO.count();
        int expected = 4;

        assertEquals(expected, count);
    }

    void insert(long playerId) {
        GameData dummyGameData = new GameData(new GameOutcome(7, GameResult.WIN), 7777L);

        String saveQuery = """
                INSERT INTO games (player_id, duration_milliseconds, result, number_of_turns)
                VALUES(?, ?, ?, ?)
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(saveQuery)) {
            preparedStatement.setLong(1, playerId);
            preparedStatement.setLong(2, dummyGameData.gameDuration());
            preparedStatement.setString(3, dummyGameData.gameOutcome().result().name());
            preparedStatement.setInt(4, dummyGameData.gameOutcome().turnsPlayed());

        } catch (SQLException exception) {
            throw new PersistenceException("Failed to save a a game’s data for a player with ID: " + playerId,
                    exception);
        }
    }
}
