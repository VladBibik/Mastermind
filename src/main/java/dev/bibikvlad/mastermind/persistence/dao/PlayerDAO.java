package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.database.DatabaseContext;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        PreparedStatement preparedStatement = connection.prepareStatement(addPlayerQuery);
        preparedStatement.setString(1, playerName);
        preparedStatement.executeUpdate();
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

    public void addLocale(String playerName) throws SQLException {
        //TODO: UNFINISHED!
        String addLocaleQuery = """
                            INSERT INTO player_configurations (language)  VALUES (?)
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(addLocaleQuery);
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