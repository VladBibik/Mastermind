package dev.bibikvlad.mastermind.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

    private static void checkIfDBIsCreatedCreateIfNot(Connection connection) throws SQLException {
        if (connection != null) {
            SchemaCreator.create(connection);
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
