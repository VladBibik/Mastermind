package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.database.DatabaseContext;
import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.mappers.PlayerMapper;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;
import dev.bibikvlad.mastermind.persistence.repository.PlayerDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcPlayerDAO implements PlayerDAO {
    private final Connection connection;

    public JdbcPlayerDAO(Connection connection) {
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
                players.add(PlayerMapper.map(resultSet));
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
            return getPlayer(preparedStatement);
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
            return getPlayer(preparedStatement);
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch player by Player Name: " + playerName, exception);
        }
    }

    private Optional<Player> getPlayer(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return Optional.of(PlayerMapper.map(resultSet));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void save(Player player) throws PersistenceException {
        String addPlayerQuery = """
                INSERT INTO players (player_name) VALUES (?)
                """;
        String addPlayerConfigQuery = """
                INSERT INTO player_configurations (player_id, language) VALUES (?, ?)
                """;

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement playerPreparedStatement =
                         connection.prepareStatement(addPlayerQuery, Statement.RETURN_GENERATED_KEYS);
                 PreparedStatement configPreparedStatement = connection.prepareStatement(addPlayerConfigQuery)) {


                playerPreparedStatement.setString(1, player.getPlayerName());
                playerPreparedStatement.executeUpdate();

                try (ResultSet generatedKeys = playerPreparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int playerId = generatedKeys.getInt(1);

                        configPreparedStatement.setInt(1, playerId);
                        configPreparedStatement.setString(2,
                                player.getPlayerConfig().getLocale().getLanguageName());
                        configPreparedStatement.executeUpdate();

                        connection.commit();
                    } else {
                        throw new PersistenceException("Creating player failed. No ID obtained.");
                    }
                }
            } catch (SQLException exception) {
                connection.rollback();

                throw new PersistenceException("Failed to save a Player", exception);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (SQLException exception) {
            throw new PersistenceException("Transaction setup failed", exception);
        }

    }

    @Override
    public void delete(Player player) throws PersistenceException {
        String deletePlayerQuery = """
                            DELETE FROM players
                            WHERE player_name = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(deletePlayerQuery)) {
            preparedStatement.setString(1, player.getPlayerName());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to delete a Player", exception);
        }
    }

    @Override
    public void deleteById(int playerId) throws PersistenceException {
        String deletePlayerQuery = """
                        DELETE FROM players
                        WHERE id = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(deletePlayerQuery)) {
            preparedStatement.setInt(1, playerId);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to delete player by ID: " + playerId, exception);
        }
    }

    @Override
    public void deleteByName(String playerName) throws PersistenceException {
        String deletePlayerQuery = """
                        DELETE FROM players
                        WHERE player_name = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(deletePlayerQuery)) {
            preparedStatement.setString(1, playerName);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to delete player by Name: " + playerName, exception);
        }
    }

    @Override
    public void update(Player player) throws PersistenceException {
        String updatePlayerQuery = """
                        UPDATE players
                        SET player_name = ?
                        WHERE id = ?;
                """;
        String updateConfigQuery = """
                        UPDATE player_configurations
                        SET language = ?
                        WHERE player_id = ?;
                """;

        try {
            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(updatePlayerQuery);
                 PreparedStatement configPreparedStatement = connection.prepareStatement(updateConfigQuery)) {

                preparedStatement.setString(1, player.getPlayerName());
                preparedStatement.setLong(2, player.getId());
                preparedStatement.executeUpdate();

                configPreparedStatement.setString(1,
                        player.getPlayerConfig().getLocale().getLanguageName());
                configPreparedStatement.setLong(2, player.getId());
                configPreparedStatement.executeUpdate();

                connection.commit();
            } catch (SQLException exception) {
                connection.rollback();

                throw new PersistenceException("Failed to update a Player: " + player, exception);
            } finally {
                connection.setAutoCommit(true);
            }
        } catch (
                SQLException exception) {
            throw new PersistenceException("Transaction setup failed", exception);
        }
    }

    @Override
    public boolean existsById(int playerId) throws PersistenceException {
        String playerQuery = """
                            SELECT player_name FROM players
                            WHERE id = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(playerQuery)) {
            preparedStatement.setInt(1, playerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to check if player with id: " + playerId + " exists", exception);
        }
    }

    @Override
    public boolean existsByName(String playerName) throws PersistenceException {
        String playerQuery = """
                            SELECT player_name FROM players
                            WHERE player_name = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(playerQuery)) {
            preparedStatement.setString(1, playerName);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to check if player with name: " + playerName + " exists", exception);
        }
    }
}

class Test {
    public static void main(String[] args) throws PersistenceException {
        JdbcPlayerDAO jdbcPlayerRepository = new JdbcPlayerDAO(DatabaseContext.getConnection());

        PlayerConfig playerConfig = new PlayerConfig(LocaleType.RUSSIAN);
        Player oldPlayer = jdbcPlayerRepository.findById(12).orElse(null);
        Player newPlayer = new Player(oldPlayer.getId(), "NewPlayer?",
                oldPlayer.getCreationDate(), playerConfig);

        jdbcPlayerRepository.update(newPlayer);

        System.out.println(jdbcPlayerRepository.findAll());
    }
}