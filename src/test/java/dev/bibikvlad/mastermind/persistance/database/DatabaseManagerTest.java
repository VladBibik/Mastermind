package dev.bibikvlad.mastermind.persistance.database;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.persistence.database.DatabaseManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseManagerTest {
    @Test
    @DisplayName("Tests if a returned connection has foreign keys enabled")
    void enablesForeignKeys() throws SQLException {
        DatabaseManager databaseManager =
                new DatabaseManager("jdbc:sqlite::memory:");

        try (Connection connection = databaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("PRAGMA foreign_keys")) {

            assertTrue(resultSet.next());
            assertEquals(1, resultSet.getInt(1));
        }
    }

    @Test
    @DisplayName("Returns an open database connection")
    void returnsOpenDatabaseConnection() throws SQLException {
        DatabaseManager databaseManager =
                new DatabaseManager("jdbc:sqlite::memory:");

        try (Connection connection = databaseManager.getConnection()) {
            assertNotNull(connection);
            assertFalse(connection.isClosed());
        }
    }

    @Test
    @DisplayName("Wraps SQLException in PersistenceException")
    void wrapsSqlException() {
        DatabaseManager databaseManager =
                new DatabaseManager("jdbc:sqlite:/invalid/path/database.db");

        PersistenceException exception = assertThrows(
                PersistenceException.class,
                databaseManager::getConnection
        );

        assertEquals(
                "Problem obtaining database connection",
                exception.getMessage()
        );

        assertInstanceOf(SQLException.class, exception.getCause());
    }
}
