package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.database.DatabaseContext;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.player.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerDAO {
    private final Connection connection;

    public PlayerDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Player> getPlayers() throws SQLException {
        List<Player> players = new ArrayList<>();
        String fetchAllPlayersQuery = """
                SELECT *
                FROM players p
                LEFT JOIN player_configurations conf
                ON p.id = conf.player_id
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(fetchAllPlayersQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Player player = new Player(
                    resultSet.getString("player_name"),
                    LocaleType.fromLanguageString(resultSet.getString("language")));

            players.add(player);
        }

        return players;
    }

    public Player getPlayerById(int playerId) throws SQLException {
        if (!existById(playerId))
            throw new SQLException("Player with id: '" + playerId + "' does not exist");

        String fetchPlayerQuery = """
                        SELECT p.player_name, conf.language
                        FROM players p
                        LEFT JOIN player_configurations conf
                        ON p.id = conf.player_id
                        WHERE p.id = ?
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(fetchPlayerQuery);
        preparedStatement.setInt(1, playerId);
        ResultSet resultSet = preparedStatement.executeQuery();

        String playerName = resultSet.getString("player_name");
        String language = resultSet.getString("language");

        return new Player(playerName, LocaleType.fromLanguageString(language));
    }

    public Player getPlayerByPlayerName(String playerName) throws SQLException {
        if (!existByPlayerName(playerName))
            throw new SQLException("Player with: '" + playerName + "' does not exist");

        String fetchPlayerQuery = """
                        SELECT p.player_name, conf.language
                        FROM players p
                        LEFT JOIN player_configurations conf
                        ON p.id = conf.player_id
                        WHERE p.player_name = ?
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(fetchPlayerQuery);
        preparedStatement.setString(1, playerName);
        ResultSet resultSet = preparedStatement.executeQuery();

        String fetchedPlayerName = resultSet.getString("player_name");
        String language = resultSet.getString("language");

        return new Player(fetchedPlayerName, LocaleType.fromLanguageString(language));
    }

    public Optional<String> getPlayerNameById(int id) throws SQLException {
        String getPlayerNameQuery = """
                        SELECT player_name
                        FROM players
                        WHERE id = ?
                """;
        Optional<String> playerName = Optional.empty();

        PreparedStatement preparedStatement = connection.prepareStatement(getPlayerNameQuery);
        preparedStatement.setInt(1, id);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            playerName = Optional.ofNullable(resultSet.getString("player_name"));
        }

        return playerName;
    }

    public void savePlayer(String playerName) throws SQLException {
        String addPlayerQuery = """
                INSERT INTO players (player_name) VALUES (?)
                """;
        String addDefaultConfigQuery = """
                INSERT INTO player_configurations (player_id, language) VALUES (?, ?)
                """;

        PreparedStatement playerPreparedStatement =
                connection.prepareStatement(addPlayerQuery, Statement.RETURN_GENERATED_KEYS);
        PreparedStatement configPreparedStatement = connection.prepareStatement(addDefaultConfigQuery);

        playerPreparedStatement.setString(1, playerName);
        playerPreparedStatement.executeUpdate();

        try (ResultSet generatedKeys = playerPreparedStatement.getGeneratedKeys()) {
            if (generatedKeys.next()) {
                int playerId = generatedKeys.getInt(1);

                configPreparedStatement.setInt(1, playerId);
                configPreparedStatement.setString(2, LocaleType.ENGLISH.getLanguageName());
                configPreparedStatement.executeUpdate();
            } else {
                throw new SQLException("Creating player failed. No ID obtained.");
            }
        }
    }

    public void deleteByPlayerName(String playerName) throws SQLException {
        if (!existByPlayerName(playerName))
            throw new SQLException("Player name: '" + playerName + "' does not exist");

        String deletePlayerQuery = """
                        DELETE FROM players
                        WHERE player_name = ?;
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(deletePlayerQuery);
        preparedStatement.setString(1, playerName);
        preparedStatement.executeUpdate();
    }

    public void deleteById(int playerId) throws SQLException {
        if (!existById(playerId))
            throw new SQLException("Player with id: '" + playerId + "' does not exist");

        String deletePlayerQuery = """
                        DELETE FROM players
                        WHERE player_name = ?;
                """;
        PreparedStatement preparedStatement = connection.prepareStatement(deletePlayerQuery);
        preparedStatement.setInt(1, playerId);
        preparedStatement.executeUpdate();
    }

    public void updateByPlayerName(String oldPlayerName, String newPlayerName) throws SQLException {
        if (!existByPlayerName(oldPlayerName))
            throw new SQLException("Player name: '" + oldPlayerName + "' does not exist");

        String updatePlayerQuery = """
                            UPDATE players
                            SET player_name = ?
                            WHERE player_name = ?;
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(updatePlayerQuery);
        preparedStatement.setString(1, newPlayerName);
        preparedStatement.setString(2, oldPlayerName);
        preparedStatement.executeUpdate();
    }

    public void updateById(int playerId, String newPlayerName) throws SQLException {
        if (!existById(playerId))
            throw new SQLException("Player with id: '" + playerId + "' does not exist");

        String updatePlayerQuery = """
                            UPDATE players
                            SET player_name = ?
                            WHERE player_name = ?;
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(updatePlayerQuery);
        preparedStatement.setString(1, newPlayerName);
        preparedStatement.setInt(2, playerId);
        preparedStatement.executeUpdate();
    }

    public void setLocale(String playerName) throws SQLException {
        //TODO: Still unfinished! Needs get player method!
        if (!existByPlayerName(playerName))
            throw new SQLException("Player name: '" + playerName + "' does not exist");

        String setLocaleQuery = """
                            INSERT INTO player_configurations (language)  VALUES (?)
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(setLocaleQuery);
        preparedStatement.setString(1, playerName);
        preparedStatement.executeUpdate();
    }

    public boolean existById(int playerId) throws SQLException {
        String playerQuery = """
                            SELECT player_name FROM players
                            WHERE id = ?;
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(playerQuery);
        preparedStatement.setInt(1, playerId);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }

    public boolean existByPlayerName(String playerName) throws SQLException {
        String playerQuery = """
                            SELECT player_name FROM players
                            WHERE player_name = ?;
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(playerQuery);
        preparedStatement.setString(1, playerName);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }
}

class Test {
    public static void main(String[] args) throws SQLException {
        PlayerDAO playerDAO = new PlayerDAO(DatabaseContext.getConnection());

        int playerId = 11;
        System.out.println(playerDAO.existByPlayerName("Player1"));
    }
}