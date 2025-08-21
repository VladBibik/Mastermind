package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.database.DatabaseContext;
import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;
import dev.bibikvlad.mastermind.persistence.repository.PlayerRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcPlayerRepository implements PlayerRepository {
    private final Connection connection;

    public JdbcPlayerRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Player> findAll() throws PersistenceException {
        List<Player> players = new ArrayList<>();
        String fetchAllPlayersQuery = """
                SELECT *
                FROM players p
                LEFT JOIN player_configurations conf
                ON p.id = conf.player_id
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(fetchAllPlayersQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PlayerConfig playerConfig = new PlayerConfig(
                        resultSet.getInt("id"),
                        LocaleType.fromLanguageString(resultSet.getString("language")));
                Player player = new Player(
                        resultSet.getString("player_name"),
                        LocalDateTime.parse(resultSet.getString("creation_date")),
                        playerConfig);

                players.add(player);
            }

            return players;
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch all players", exception);
        }
    }

    @Override
    public Optional<Player> findById(int playerId) throws PersistenceException {
        String fetchPlayerQuery = """
                        SELECT *
                        FROM players p
                        LEFT JOIN player_configurations conf
                        ON p.id = conf.player_id
                        WHERE p.id = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(fetchPlayerQuery)) {
            preparedStatement.setInt(1, playerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                PlayerConfig playerConfig = new PlayerConfig(
                        resultSet.getInt("id"),
                        LocaleType.fromLanguageString(resultSet.getString("language")));
                Player player = new Player(
                        resultSet.getString("player_name"),
                        LocalDateTime.parse(resultSet.getString("creation_date")),
                        playerConfig);

                return Optional.of(player);
            } else {
                return Optional.empty();
            }
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch player by ID: " + playerId, exception);
        }
    }

    @Override
    public Optional<Player> findByName(String playerName) throws PersistenceException {
        String fetchPlayerQuery = """
                        SELECT *
                        FROM players p
                        LEFT JOIN player_configurations conf
                        ON p.id = conf.player_id
                        WHERE p.player_name = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(fetchPlayerQuery)) {
            preparedStatement.setString(1, playerName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                PlayerConfig playerConfig = new PlayerConfig(
                        resultSet.getInt("id"),
                        LocaleType.fromLanguageString(resultSet.getString("language")));
                Player player = new Player(
                        resultSet.getString("player_name"),
                        LocalDateTime.parse(resultSet.getString("creation_date")),
                        playerConfig);

                return Optional.of(player);
            } else {
                return Optional.empty();
            }
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch player by Player Name: " + playerName, exception);
        }
    }

//    public Optional<String> getPlayerNameById(int id) throws PersistenceException {
//        String getPlayerNameQuery = """
//                        SELECT player_name
//                        FROM players
//                        WHERE id = ?
//                """;
//        Optional<String> playerName = Optional.empty();
//
//        PreparedStatement preparedStatement = connection.prepareStatement(getPlayerNameQuery);
//        preparedStatement.setInt(1, id);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        if (resultSet.next()) {
//            playerName = Optional.ofNullable(resultSet.getString("player_name"));
//        }
//
//        return playerName;
//    }

    @Override
    public void save(Player player) throws PersistenceException {
        String addPlayerQuery = """
                INSERT INTO players (player_name) VALUES (?)
                """;
        String addPlayerConfigQuery = """
                INSERT INTO player_configurations (player_id, language) VALUES (?, ?)
                """;

        try (PreparedStatement playerPreparedStatement =
                     connection.prepareStatement(addPlayerQuery, Statement.RETURN_GENERATED_KEYS)) {
            PreparedStatement configPreparedStatement = connection.prepareStatement(addPlayerConfigQuery);

            playerPreparedStatement.setString(1, player.getPlayerName());
            playerPreparedStatement.executeUpdate();

            try (ResultSet generatedKeys = playerPreparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int playerId = generatedKeys.getInt(1);

                    configPreparedStatement.setInt(1, playerId);
                    configPreparedStatement.setString(2,
                            player.getPlayerConfig().getLocale().getLanguageName());
                    configPreparedStatement.executeUpdate();
                } else {
                    throw new PersistenceException("Creating player failed. No ID obtained.");
                }
            }
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to save a Player", exception);
        }
    }

    @Override
    public void delete(Player player) {

    }

    @Override
    public void deleteById(int playerId) throws PersistenceException {
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

    @Override
    public void deleteByName(String playerName) throws PersistenceException {
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

    @Override
    public void update(Player oldPlayer, Player newPlayer) {

    }

    public void updateByPlayerName(String oldPlayerName, String newPlayerName) throws PersistenceException {
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

    public void updateById(int playerId, String newPlayerName) throws PersistenceException {
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

//    public void setLocale(String playerName) throws PersistenceException {
//        //TODO: Still unfinished! Needs get player method!
//        if (!existByPlayerName(playerName))
//            throw new SQLException("Player name: '" + playerName + "' does not exist");
//
//        String setLocaleQuery = """
//                            INSERT INTO player_configurations (language)  VALUES (?)
//                """;
//
//        PreparedStatement preparedStatement = connection.prepareStatement(setLocaleQuery);
//        preparedStatement.setString(1, playerName);
//        preparedStatement.executeUpdate();
//    }

    public boolean existById(int playerId) throws PersistenceException {
        String playerQuery = """
                            SELECT player_name FROM players
                            WHERE id = ?;
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(playerQuery);
        preparedStatement.setInt(1, playerId);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }

    public boolean existByPlayerName(String playerName) throws PersistenceException {
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
    public static void main(String[] args) throws PersistenceException {
        JdbcPlayerRepository jdbcPlayerRepository = new JdbcPlayerRepository(DatabaseContext.getConnection());

        System.out.println(jdbcPlayerRepository.findAll());
    }
}