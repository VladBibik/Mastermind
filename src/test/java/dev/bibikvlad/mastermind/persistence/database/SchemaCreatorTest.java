package dev.bibikvlad.mastermind.persistence.database;

import dev.bibikvlad.mastermind.localization.config.LocalizationType;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
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

            try (ResultSet resultSet = statement.executeQuery()) {
                assertTrue(resultSet.next());
                assertEquals(playerName, resultSet.getString("player_name"));
                assertFalse(resultSet.next());
            }
        }
    }

    @Test
    @DisplayName("Creates the 'player_configurations' table")
    void createsPlayersConfigurationTable() throws SQLException {
        String playerName = "TEST_PLAYER";

        String insertPlayerQuery = """
                INSERT INTO players (player_name)
                VALUES (?)
                """;

        String selectPlayerQuery = """
                SELECT player_id
                FROM players
                WHERE player_name = ?
                """;

        String insertPlayerConfigQuery = """
                INSERT INTO player_configurations (player_id, language, logo_border_color, logo_main_color,
                logo_accent_color, logo_background_color)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        String selectPlayerConfigQuery = """
                SELECT *
                FROM player_configurations
                WHERE player_id = ?
                """;

        long playerId;

        try (PreparedStatement statement = connection.prepareStatement(insertPlayerQuery)) {
            statement.setString(1, playerName);
            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(selectPlayerQuery)) {
            statement.setString(1, playerName);

            try (var resultSet = statement.executeQuery()) {
                resultSet.next();

                playerId = resultSet.getLong("player_id");
            }
        }

        try (PreparedStatement statement = connection.prepareStatement(insertPlayerConfigQuery)) {
            statement.setLong(1, playerId);
            statement.setString(2, LocalizationType.ENGLISH.getNativeDisplayName());
            statement.setString(3, ConsoleColor.ORANGE.getDisplayName());
            statement.setString(4, ConsoleColor.ORANGE.getDisplayName());
            statement.setString(5, ConsoleColor.ORANGE.getDisplayName());
            statement.setString(6, ConsoleColor.BACKGROUND_BLACK.getDisplayName());

            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(selectPlayerConfigQuery)) {
            statement.setLong(1, playerId);

            try (ResultSet resultSet = statement.executeQuery()) {
                assertTrue(resultSet.next());
                assertEquals(LocalizationType.ENGLISH.getNativeDisplayName(),
                        resultSet.getString("language"));
                assertEquals(ConsoleColor.ORANGE.getDisplayName(),
                        resultSet.getString("logo_border_color"));
                assertEquals(ConsoleColor.ORANGE.getDisplayName(),
                        resultSet.getString("logo_main_color"));
                assertEquals(ConsoleColor.ORANGE.getDisplayName(),
                        resultSet.getString("logo_accent_color"));
                assertEquals(ConsoleColor.BACKGROUND_BLACK.getDisplayName(),
                        resultSet.getString("logo_background_color"));
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

    @Test
    @DisplayName("Rejects null player names")
    void rejectsNullPlayerNames() throws SQLException {
        String query = """
                INSERT INTO players (player_name)
                VALUES (?)
                """;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, null);

            assertThrows(SQLException.class, statement::executeUpdate);
        }
    }

    @Test
    @DisplayName("Rejects player names that contains more than 100 symbols")
    void rejectsPlayerNamesMoreThanHundredSymbolsLong() throws SQLException {
        String query = """
                INSERT INTO players (player_name)
                VALUES (?)
                """;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "REALLY_LOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO" +
                    "OOOOOOOOOOOOOOOOOOOOOONG_PLAYER_NAME");

            assertThrows(SQLException.class, statement::executeUpdate);
        }
    }
}
