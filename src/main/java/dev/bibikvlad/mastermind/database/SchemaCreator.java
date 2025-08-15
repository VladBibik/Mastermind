package dev.bibikvlad.mastermind.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaCreator {
    public static void create(Connection connection) throws SQLException {
        createUsersTable(connection);
        createUserConfigurationTable(connection);
    }

    private static void createUsersTable(Connection connection) throws SQLException {
        String createUsersTable = """
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    username TEXT UNIQUE NOT NULL,
                    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                );""";

        Statement statement = connection.createStatement();
        statement.executeUpdate(createUsersTable);
    }

    private static void createUserConfigurationTable(Connection connection) throws SQLException {
        String createUserConfigTable = """
                    CREATE TABLE IF NOT EXISTS user_configurations (
                        user_id INTEGER PRIMARY KEY,
                        language TEXT NOT NULL,
                        FOREIGN KEY (user_id) REFERENCES users(id)
                );""";

        Statement statement = connection.createStatement();

        statement.executeUpdate(createUserConfigTable);
    }
}
