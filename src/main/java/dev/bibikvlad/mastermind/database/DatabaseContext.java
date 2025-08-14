package dev.bibikvlad.mastermind.database;

import java.sql.*;

public class DatabaseContext {
    private static final String DB_URL = "jdbc:sqlite:Mastermind.db";

    public static Statement getStatement() {
        try {
            Connection connection = DriverManager.getConnection(DB_URL);

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
                }

                return connection;
            }
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
        }

        throw new IllegalStateException("Something went wrong");
    }

    public static PreparedStatement getPreparedStatement() {

    }

    private boolean isDBCreated() {

    }
}

class TEST {
    public static void main(String[] args) {
        Connection connection = DatabaseContext.getConnection();
    }
}
