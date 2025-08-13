package dev.bibikvlad.mastermind.game.TEMP_DB_TESTING;

import java.sql.*;

public class DB_TESTING {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:test.db";

        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {
//                String createTable = """
//                        CREATE TABLE IF NOT EXISTS adventurers (
//                            id INTEGER PRIMARY KEY,
//                            name VARCHAR(255),
//                            class VARCHAR(255)
//                        )
//                        """;
//
//                try (Statement statement = connection.createStatement()) {
//                    statement.execute(createTable);
//                }
//
//                String insertAdventurer = "INSERT INTO adventurers (id, name, class) VALUES (?, ?, ?)";
//
//                try (PreparedStatement preparedStatement = connection.prepareStatement(insertAdventurer)) {
//                    preparedStatement.setInt(1, 2);
//                    preparedStatement.setString(2, "Lina");
//                    preparedStatement.setString(3, "Mage");
//
//                    preparedStatement.executeUpdate();
//                }

                String selectAdventurer = "SELECT * FROM adventurers";

                try (Statement preparedStatement = connection.createStatement()) {
                    ResultSet resultSet = preparedStatement.executeQuery(selectAdventurer);

                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String className = resultSet.getString("class");

                        System.out.println(id + " " + name + " " + className);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
