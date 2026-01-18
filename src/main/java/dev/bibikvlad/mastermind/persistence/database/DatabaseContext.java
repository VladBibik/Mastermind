package dev.bibikvlad.mastermind.persistence.database;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseContext {
    private static final String DB_URL = "jdbc:sqlite:Mastermind.db";

    public static Connection getConnection() throws PersistenceException {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            try (Statement statement = connection.createStatement()) {
                statement.execute("PRAGMA foreign_keys=ON");
            }

            return connection;
        } catch (SQLException exception) {
            throw new PersistenceException("Problem obtaining database connection", exception);
        }
    }

    //TODO: Move this method to the custom class when app start point is decided!
    public static void initialize() {
        try (Connection connection = getConnection()) {
            SchemaCreator.create(connection);
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to initialize database schema", exception);
        }
    }
}