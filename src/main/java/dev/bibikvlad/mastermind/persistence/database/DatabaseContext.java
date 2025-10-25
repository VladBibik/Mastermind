package dev.bibikvlad.mastermind.persistence.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseContext {
    private static final String DB_URL = "jdbc:sqlite:Mastermind.db" ;

    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL);
        Statement statement = connection.createStatement();

        statement.execute("PRAGMA foreign_keys=ON");

        return connection;
    }

    //TODO: Move this method to the custom class when app start point is decided!
    public static void initialize() {
        try (Connection connection = getConnection()) {
            SchemaCreator.create(connection);
        } catch (SQLException exception) {
            throw new IllegalStateException("Failed to initialize database schema", exception);
        }
    }
}