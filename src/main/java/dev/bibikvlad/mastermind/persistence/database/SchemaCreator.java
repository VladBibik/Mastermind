package dev.bibikvlad.mastermind.persistence.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class SchemaCreator {
    public static void create(Connection connection) throws SQLException {
        createPlayersTable(connection);
        createPlayerConfigurationTable(connection);
        createPlayerLastSelectedTable(connection);
        createGamesTable(connection);
    }

    private static void createPlayersTable(Connection connection) throws SQLException {
        String createPlayersTableQuery = """
                CREATE TABLE IF NOT EXISTS players (
                    player_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    player_name TEXT UNIQUE NOT NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
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
                    FOREIGN KEY (player_id) REFERENCES players(player_id) ON DELETE CASCADE
                );""";

        Statement statement = connection.createStatement();

        statement.executeUpdate(createPlayerConfigTableQuery);
    }

    private static void createPlayerLastSelectedTable(Connection connection) throws SQLException {
        String createPlayerLastSelectedTableQuery = """
                CREATE TABLE IF NOT EXISTS player_last_selected (
                    player_id INTEGER PRIMARY KEY,
                    last_selected_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (player_id) REFERENCES players(player_id) ON DELETE CASCADE
                );""";

        Statement statement = connection.createStatement();

        statement.executeUpdate(createPlayerLastSelectedTableQuery);
    }

    private static void createGamesTable(Connection connection) throws SQLException {
        String createGamesTableQuery = """
                CREATE TABLE IF NOT EXISTS games (
                    game_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    player_id INTEGER NOT NULL,
                    duration_seconds INTEGER NOT NULL,
                    is_canceled BOOLEAN NOT NULL,
                    is_win BOOLEAN NOT NULL,
                    number_of_turns INTEGER NOT NULL,
                    started_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (player_id) REFERENCES players(player_id) ON DELETE CASCADE
                );""";

        Statement statement = connection.createStatement();

        statement.executeUpdate(createGamesTableQuery);
    }
}
