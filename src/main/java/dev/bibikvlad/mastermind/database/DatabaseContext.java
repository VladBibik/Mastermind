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
        if (connection != null) {
            String createTable = """
                    CREATE TABLE IF NOT EXISTS users (
                        id INTEGER PRIMARY KEY AUTOINCREMENT,
                        username TEXT NOT NULL,
                        creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    );
                    """;
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate(createTable);
            } catch (SQLException exception) {
                System.out.println(exception.getMessage());
            }
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
