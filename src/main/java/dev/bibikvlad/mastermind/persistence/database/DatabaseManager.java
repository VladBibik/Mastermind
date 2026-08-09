package dev.bibikvlad.mastermind.persistence.database;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private final String databaseUrl;

    public DatabaseManager(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    public Connection getConnection() throws PersistenceException {
        try {
            Connection connection = DriverManager.getConnection(databaseUrl);
            try (Statement statement = connection.createStatement()) {
                statement.execute("PRAGMA foreign_keys=ON");
            }
            return connection;
        } catch (SQLException exception) {
            throw new PersistenceException("Problem obtaining database connection", exception);
        }
    }

    public void initialize() {
        try (Connection connection = getConnection()) {
            SchemaCreator.create(connection);
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to initialize database schema", exception);
        }
    }
}