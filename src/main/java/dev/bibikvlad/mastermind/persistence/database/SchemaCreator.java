package dev.bibikvlad.mastermind.persistence.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaCreator {
    public static void create(Connection connection) throws SQLException {
        createPlayersTable(connection);
        createPlayerConfigurationTable(connection);
        createPlayerLastSelectedTable(connection);
    }

    private static void createPlayersTable(Connection connection) throws SQLException {
        String createPlayersTableQuery = """
                CREATE TABLE IF NOT EXISTS players (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    player_name TEXT UNIQUE NOT NULL,
                    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    CHECK (length(player_name) <= 100)
                );""";

        Statement statement = connection.createStatement();
        statement.executeUpdate(createPlayersTableQuery);
    }

    private static void createPlayerConfigurationTable(Connection connection) throws SQLException {
        String createPlayerConfigTableQuery = """
                    CREATE TABLE IF NOT EXISTS player_configurations (
                        player_id INTEGER PRIMARY KEY,
                        language TEXT NOT NULL,
                        logo_border_color TEXT NOT NULL,
                        logo_main_color TEXT NOT NULL,
                        logo_accent_color TEXT NOT NULL,
                        logo_background_color TEXT NOT NULL,
                        FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE
                );""";

        Statement statement = connection.createStatement();

        statement.executeUpdate(createPlayerConfigTableQuery);
    }

    private static void createPlayerLastSelectedTable(Connection connection) throws SQLException {
        String createPlayerLastSelectedTableQuery = """
                CREATE TABLE IF NOT EXISTS player_last_selected (
                    player_id INTEGER PRIMARY KEY,
                    last_selected_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (player_id) REFERENCES players(id) ON DELETE CASCADE
                );""";

        Statement statement = connection.createStatement();

        statement.executeUpdate(createPlayerLastSelectedTableQuery);
    }
}
