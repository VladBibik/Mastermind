package dev.bibikvlad.mastermind.persistence.database;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SchemaCreatorTest {
    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite::memory:");

        SchemaCreator.create(connection);
    }

    @AfterEach
    void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    @DisplayName("Inserts data into the 'players' table")
    void insertsDataIntoPlayersTable() throws SQLException {
        String newPlayerQuery = """
                INSERT INTO players (player_name)
                VALUES(?)
                """;
        String playerName = "TEST_PLAYER";
        int rowsAffected;

        try (PreparedStatement preparedStatement = connection.prepareStatement(newPlayerQuery)) {
            preparedStatement.setString(1, playerName);

            rowsAffected = preparedStatement.executeUpdate();
        }

        assertEquals(1, rowsAffected);
    }
}
