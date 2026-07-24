package dev.bibikvlad.mastermind.persistence.database;

import dev.bibikvlad.mastermind.app.bootstrap.path.DatabasePathResolver;
import dev.bibikvlad.mastermind.exceptions.PersistenceException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    //TODO: Maybe we should make DatabasePathResolver static?
    private static final String DATABASE_URL =
            "jdbc:sqlite:" + new DatabasePathResolver().getDatabasePath();

    private DatabaseManager() {
        throw new AssertionError("Cannot instantiate DatabaseManager");
    }

    public static Connection getConnection() throws PersistenceException {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URL);
            try (Statement statement = connection.createStatement()) {
                statement.execute("PRAGMA foreign_keys=ON");
            }
            return connection;
        } catch (SQLException exception) {
            throw new PersistenceException("Problem obtaining database connection", exception);
        }
    }

    public static void initialize() {
        try (Connection connection = getConnection()) {
            SchemaCreator.create(connection);
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to initialize database schema", exception);
        }
    }
}