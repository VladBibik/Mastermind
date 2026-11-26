package dev.bibikvlad.mastermind.persistence.database;

import dev.bibikvlad.mastermind.game.data.GameResult;
import dev.bibikvlad.mastermind.localization.config.LocalizationType;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;

public class SchemaCreatorTest {
    private String playerName;
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        playerName = "TEST_PLAYER";

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
    void createsPlayerConfigurationsTable() throws SQLException {
        String playerName = "TEST_PLAYER";

        String insertPlayerQuery = """
                INSERT INTO players (player_name)
                VALUES (?)
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

        long playerId = 1L;

        try (PreparedStatement statement = connection.prepareStatement(insertPlayerQuery)) {
            statement.setString(1, playerName);
            statement.executeUpdate();
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
    @DisplayName("Creates the 'player_last_selected' table")
    void createsPlayerLastSelectedTable() throws SQLException {
        String playerName = "TEST_PLAYER";

        String insertPlayerQuery = """
                INSERT INTO players (player_name)
                VALUES (?)
                """;

        String insertPlayerLastSelectedQuery = """
                INSERT INTO player_last_selected (player_id)
                VALUES (?)
                """;

        String selectPlayerLastSelected = """
                SELECT *
                FROM player_last_selected
                """;

        try (PreparedStatement statement = connection.prepareStatement(insertPlayerQuery)) {
            statement.setString(1, playerName);
            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(insertPlayerLastSelectedQuery)) {
            statement.setLong(1, 1L);
            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(selectPlayerLastSelected)) {
            ResultSet resultSet = statement.executeQuery();

            assertTrue(resultSet.next());
            assertEquals(1L, resultSet.getLong("player_id"));
            assertFalse(resultSet.next());
        }
    }

    @Test
    @DisplayName("Creates the 'games' table")
    void createsGamesTable() throws SQLException {
        String playerName = "TEST_PLAYER";

        String insertPlayerQuery = """
                INSERT INTO players (player_name)
                VALUES (?)
                """;

        String insertGames = """
                INSERT INTO games (player_id, duration_milliseconds, result, number_of_turns)
                VALUES (?, ?, ?, ?)
                """;

        String selectGames = """
                SELECT *
                FROM games
                """;

        try (PreparedStatement statement = connection.prepareStatement(insertPlayerQuery)) {
            statement.setString(1, playerName);
            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(insertGames)) {
            statement.setLong(1, 1L);
            statement.setLong(2, 777_777_777L);
            statement.setString(3, GameResult.WIN.name());
            statement.setInt(4, 7);

            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(selectGames)) {
            ResultSet resultSet = statement.executeQuery();

            assertTrue(resultSet.next());
            assertEquals(1L, resultSet.getLong("player_id"));
            assertEquals(777_777_777L, resultSet.getLong("duration_milliseconds"));
            assertEquals(GameResult.WIN.name(), resultSet.getString("result"));
            assertEquals(7, resultSet.getLong("number_of_turns"));

            assertFalse(resultSet.next());
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
    void rejectsPlayerNamesLongerThan100Characters() throws SQLException {
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

    @Test
    @DisplayName("Deletes 'player_configurations' entry on cascade delete")
    void deletesPlayerConfigurationsOnCascadeDelete() throws SQLException {
        String playerName = "TEST_PLAYER";

        String insertPlayerQuery = """
                INSERT INTO players (player_name)
                VALUES (?)
                """;

        String deletePlayerQuery = """
                DELETE
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
                """;

        try (PreparedStatement statement = connection.prepareStatement(insertPlayerQuery)) {
            statement.setString(1, playerName);
            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(insertPlayerConfigQuery)) {
            statement.setLong(1, 1L);
            statement.setString(2, LocalizationType.ENGLISH.getNativeDisplayName());
            statement.setString(3, ConsoleColor.ORANGE.getDisplayName());
            statement.setString(4, ConsoleColor.ORANGE.getDisplayName());
            statement.setString(5, ConsoleColor.ORANGE.getDisplayName());
            statement.setString(6, ConsoleColor.BACKGROUND_BLACK.getDisplayName());

            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(deletePlayerQuery)) {
            statement.setString(1, playerName);

            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(selectPlayerConfigQuery)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                assertFalse(resultSet.next());
            }
        }
    }

    @Test
    @DisplayName("Deletes 'player_last_selected' entry on cascade delete")
    void deletesPlayerLastSelectedOnCascadeDelete() throws SQLException {
        String playerName = "TEST_PLAYER";

        String insertPlayerQuery = """
                INSERT INTO players (player_name)
                VALUES (?)
                """;

        String deletePlayerQuery = """
                DELETE
                FROM players
                WHERE player_name = ?
                """;

        String insertPlayerLastSelected = """
                INSERT INTO player_last_selected (player_id)
                VALUES (?)
                """;

        String selectPlayerLastSelected = """
                SELECT *
                FROM player_last_selected
                """;

        try (PreparedStatement statement = connection.prepareStatement(insertPlayerQuery)) {
            statement.setString(1, playerName);
            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(insertPlayerLastSelected)) {
            statement.setLong(1, 1L);
            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(deletePlayerQuery)) {
            statement.setString(1, playerName);
            statement.executeUpdate();
        }

        try (PreparedStatement statement = connection.prepareStatement(selectPlayerLastSelected)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                assertFalse(resultSet.next());
            }
        }
    }

    @Test
    @DisplayName("Deletes 'games' entry on cascade delete")
    void deletesGamesOnCascadeDelete() throws SQLException {
        String insertGamesQuery = """
                INSERT INTO games (player_id, duration_milliseconds, result, number_of_turns)
                VALUES (?, ?, ?, ?)
                """;

        String selectGamesQuery = """
                SELECT *
                FROM player_last_selected
                """;

        try (PreparedStatement statement = connection.prepareStatement(insertGamesQuery)) {
            statement.setLong(1, insertPlayer());
            statement.setLong(2, 777_777_777L);
            statement.setString(3, GameResult.WIN.name());
            statement.setInt(4, 7);

            statement.executeUpdate();
        }

        deletePlayer();

        try (PreparedStatement statement = connection.prepareStatement(selectGamesQuery)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                assertFalse(resultSet.next());
            }
        }
    }

    @Test
    @DisplayName("Rejects unspecified entry for 'result'")
    void rejectsInvalidGameResult() throws SQLException {
        String insertGamesQuery = """
                INSERT INTO games (player_id, duration_milliseconds, result, number_of_turns)
                VALUES (?, ?, ?, ?)
                """;

        try (PreparedStatement statement = connection.prepareStatement(insertGamesQuery)) {
            statement.setLong(1, insertPlayer());
            statement.setLong(2, 777_777_777L);
            statement.setString(3, "UNSPECIFIED_STRING");
            statement.setInt(4, 7);

            assertThrows(SQLException.class, statement::executeUpdate);
        }
    }

    private long insertPlayer() throws SQLException {
        String sql = """
            INSERT INTO players (player_name)
            VALUES (?)
            """;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, playerName);
            statement.executeUpdate();
        }

        String selectSql = """
            SELECT player_id
            FROM players
            WHERE player_name = ?
            """;

        try (PreparedStatement statement = connection.prepareStatement(selectSql)) {
            statement.setString(1, playerName);

            try (ResultSet resultSet = statement.executeQuery()) {
                assertTrue(resultSet.next());
                return resultSet.getLong("player_id");
            }
        }
    }

    private void deletePlayer() throws SQLException {
        String deletePlayerQuery = """
                DELETE
                FROM players
                WHERE player_name = ?
                """;

        try (PreparedStatement statement = connection.prepareStatement(deletePlayerQuery)) {
            statement.setString(1, playerName);
            statement.executeUpdate();
        }
    }
}
