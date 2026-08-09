package dev.bibikvlad.mastermind.persistance.database;

import dev.bibikvlad.mastermind.persistence.database.DatabaseManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
}
