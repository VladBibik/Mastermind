package dev.bibikvlad.mastermind.persistence.database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class SchemaCreatorTest {
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");

        try (Statement statement = connection.createStatement()) {
            statement.execute("PRAGMA foreign_keys = ON");
        }

        SchemaCreator.create(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    @DisplayName("Creates the 'players' table")
    void createsPlayersTable() throws SQLException {
        String playerName = "TEST_PLAYER";

        String insertQuery = """
            INSERT INTO players (player_name)
            VALUES (?)
            """;

        String selectQuery = """
            SELECT player_name
            FROM players
            WHERE player_name = ?
            """;

        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setString(1, playerName);
            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, playerName);

            try (var resultSet = statement.executeQuery()) {
                assertTrue(resultSet.next());
                assertEquals(playerName, resultSet.getString("player_name"));
                assertFalse(resultSet.next());
            }
        }
    }

    @Test
    @DisplayName("Rejects duplicate player names")
    void rejectsDuplicatePlayerNames() throws SQLException {
        String query = """
            INSERT INTO players (player_name)
            VALUES (?)
            """;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "TEST_PLAYER");
            statement.executeUpdate();

            statement.setString(1, "TEST_PLAYER");

            assertThrows(SQLException.class, statement::executeUpdate);
        }
    }
}
