package dev.bibikvlad.mastermind.persistence.database;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseContext {
    private static final String DB_URL = "jdbc:sqlite:Mastermind.db";

    private static boolean initialized = false;

    private DatabaseContext() {
        throw new AssertionError("Cannot instantiate DatabaseContext");
    }

    public static Connection getConnection() throws PersistenceException {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);
            if (!initialized) {
                SchemaCreator.create(connection);
                initialized = true;
            }

            try (Statement statement = connection.createStatement()) {
                statement.execute("PRAGMA foreign_keys=ON");
            }

            return connection;
        } catch (SQLException exception) {
            throw new PersistenceException("Problem obtaining database connection", exception);
        }
    }
}