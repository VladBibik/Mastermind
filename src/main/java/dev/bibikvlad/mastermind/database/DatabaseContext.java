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

    private static void checkIfDBIsCreatedCreateIfNot(Connection connection) throws SQLException {
        if (connection != null) {
            SchemaCreator.create(connection);
        }
    }
}


class TEST {
    public static void main(String[] args) throws SQLException {
        Connection connection = DatabaseContext.getConnection();

//        PreparedStatement insertIntoPlayers =  connection.prepareStatement(
//                "INSERT INTO players (player_name) VALUES (?)");
//        insertIntoPlayers.setString(1, "Player1");
//        insertIntoPlayers.executeUpdate();

//        PreparedStatement insertIntoConfigs =   connection.prepareStatement(
//                "INSERT INTO player_configurations (player_id, language) VALUES (?, ?)");
//        insertIntoConfigs.setInt(1, 1);
//        insertIntoConfigs.setString(2, "ENGLISH");
//        insertIntoConfigs.executeUpdate();

        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM players p" +
                " LEFT JOIN player_configurations conf ON p.id = conf.player_id");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString("player_name") + " "
                    + resultSet.getString("language"));
        }
    }
}