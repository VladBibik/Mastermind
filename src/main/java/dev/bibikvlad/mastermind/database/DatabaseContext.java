package dev.bibikvlad.mastermind.database;

import java.sql.*;

public class DatabaseContext {
    private static final String DB_URL = "jdbc:sqlite:Mastermind.db";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);

            checkIfDBIsCreatedCreateIfNot(connection);

            return connection;
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        throw new IllegalStateException("Something went wrong");
    }

    private static void checkIfDBIsCreatedCreateIfNot(Connection connection) {
        try {
            if (connection != null) {
                try (Statement statement = connection.createStatement()) {
                    statement.execute("PRAGMA foreign_keys = ON;");
                }

                String createUsersTable = """
                        CREATE TABLE IF NOT EXISTS users (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            username TEXT UNIQUE NOT NULL,
                            creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                        );""";
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(createUsersTable);
                }

                String createUserConfigTable = """
                            CREATE TABLE IF NOT EXISTS user_configurations (
                                user_id INTEGER PRIMARY KEY,
                                language TEXT NOT NULL,
                                FOREIGN KEY (user_id) REFERENCES users(id)
                        );""";

                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(createUserConfigTable);
                }
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }
    }
}

class TEST {
    public static void main(String[] args) {
        Connection connection = DatabaseContext.getConnection();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO users (username) VALUES (?)");

            preparedStatement.setString(1, "GIGA_USER");

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
