package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.persistence.database.DatabaseContext;
import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.mappers.PlayerMapper;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;
import dev.bibikvlad.utils.formatters.SQLiteTimestampFormatter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlayerJdbcDAO implements PlayerDAO {
    private final Connection connection;

    public PlayerJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Player> findAll() throws PersistenceException {
        List<Player> players = new ArrayList<>();
        String fetchAllPlayersQuery = """
                SELECT id, player_name, creation_date, player_id, language, logo_border_color,
                       logo_main_color, logo_accent_color, logo_background_color
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
    public Optional<Player> findById(long playerId) throws PersistenceException {
        String fetchPlayerQuery = """
                        SELECT id, player_name, creation_date, player_id, language, logo_border_color,
                               logo_main_color, logo_accent_color, logo_background_color
                        FROM players p
                        LEFT JOIN player_configurations conf
                        ON p.id = conf.player_id
                        WHERE p.id = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(fetchPlayerQuery)) {
            preparedStatement.setLong(1, playerId);
            return getPlayer(preparedStatement);
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch player by ID: " + playerId, exception);
        }
    }

    @Override
    public Optional<Player> findByName(String playerName) throws PersistenceException {
        String fetchPlayerQuery = """
                        SELECT id, player_name, creation_date, player_id, language, logo_border_color,
                               logo_main_color, logo_accent_color, logo_background_color
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
    public boolean save(Player player) throws PersistenceException {
        String addPlayerQuery = "INSERT INTO players (player_name) VALUES (?)";
        String addPlayerConfigQuery = "INSERT INTO player_configurations (player_id, language, logo_border_color,  " +
                "logo_main_color, logo_accent_color, logo_background_color) VALUES (?, ?, ?, ?, ?, ?)";
        int rowsUpdated;

        try (PreparedStatement playerPreparedStatement =
                     connection.prepareStatement(addPlayerQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement configPreparedStatement = connection.prepareStatement(addPlayerConfigQuery)) {


            playerPreparedStatement.setString(1, player.getPlayerName());
            playerPreparedStatement.executeUpdate();

            try (ResultSet generatedKeys = playerPreparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long playerId = generatedKeys.getLong(1);

                    configPreparedStatement.setLong(1, playerId);
                    configPreparedStatement.setString(2,
                            player.getPlayerConfig().getLocale().getLanguageName());
                    configPreparedStatement.setString(3, player.getPlayerConfig()
                            .getLogoBorderColor().getDisplayName());
                    configPreparedStatement.setString(4, player.getPlayerConfig()
                            .getLogoMainColor().getDisplayName());
                    configPreparedStatement.setString(5, player.getPlayerConfig()
                            .getLogoAccentColor().getDisplayName());
                    configPreparedStatement.setString(6, player.getPlayerConfig()
                            .getLogoBackgroundColor().getDisplayName());
                    rowsUpdated = configPreparedStatement.executeUpdate();
                } else {
                    throw new PersistenceException("Creating player failed. No ID obtained.");
                }
            }
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to save a Player: " + player, exception);
        }

        return rowsUpdated > 0;
    }

    @Override
    public boolean delete(Player player) throws PersistenceException {
        String deletePlayerQuery = """
                            DELETE FROM players
                            WHERE player_name = ?;
                """;
        int rowsUpdated;

        try (PreparedStatement preparedStatement = connection.prepareStatement(deletePlayerQuery)) {
            preparedStatement.setString(1, player.getPlayerName());
            rowsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to delete a Player: " + player, exception);
        }

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteById(long playerId) throws PersistenceException {
        String deletePlayerQuery = """
                        DELETE FROM players
                        WHERE id = ?;
                """;
        int rowsUpdated;

        try (PreparedStatement preparedStatement = connection.prepareStatement(deletePlayerQuery)) {
            preparedStatement.setLong(1, playerId);
            rowsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to delete player by ID: " + playerId, exception);
        }

        return rowsUpdated > 0;
    }

    @Override
    public boolean deleteByName(String playerName) throws PersistenceException {
        String deletePlayerQuery = """
                        DELETE FROM players
                        WHERE player_name = ?;
                """;
        int rowsUpdated;

        try (PreparedStatement preparedStatement = connection.prepareStatement(deletePlayerQuery)) {
            preparedStatement.setString(1, playerName);
            rowsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to delete player by Name: " + playerName, exception);
        }

        return rowsUpdated > 0;
    }

    @Override
    public boolean update(Player player) throws PersistenceException {
        String updatePlayerQuery = """
                        UPDATE players
                        SET player_name = ?
                        WHERE id = ?;
                """;
        String updateConfigQuery = """
                        UPDATE player_configurations
                        SET language = ?, logo_border_color = ?,
                            logo_main_color = ?, logo_accent_color = ?, logo_background_color = ?
                        WHERE player_id = ?;
                """;
        int rowsUpdated;

        try (PreparedStatement preparedStatement = connection.prepareStatement(updatePlayerQuery);
             PreparedStatement configPreparedStatement = connection.prepareStatement(updateConfigQuery)) {

            preparedStatement.setString(1, player.getPlayerName());
            preparedStatement.setLong(2, player.getId());
            preparedStatement.executeUpdate();

            configPreparedStatement.setString(1,
                    player.getPlayerConfig().getLocale().getLanguageName());
            configPreparedStatement.setString(2, player.getPlayerConfig()
                    .getLogoBorderColor().getDisplayName());
            configPreparedStatement.setString(3, player.getPlayerConfig()
                    .getLogoMainColor().getDisplayName());
            configPreparedStatement.setString(4, player.getPlayerConfig()
                    .getLogoAccentColor().getDisplayName());
            configPreparedStatement.setString(5, player.getPlayerConfig()
                    .getLogoBackgroundColor().getDisplayName());
            configPreparedStatement.setLong(6, player.getId());
            rowsUpdated = configPreparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to update a Player: " + player, exception);
        }

        return rowsUpdated > 0;
    }

    @Override
    public boolean existsById(long playerId) throws PersistenceException {
        String playerQuery = """
                            SELECT 1 FROM players
                            WHERE id = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(playerQuery)) {
            preparedStatement.setLong(1, playerId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to check if player with id: " + playerId + " exists", exception);
        }
    }

    @Override
    public boolean existsByName(String playerName) throws PersistenceException {
        String playerQuery = """
                            SELECT 1 FROM players
                            WHERE player_name = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(playerQuery)) {
            preparedStatement.setString(1, playerName);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next();
            }
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to check if player with name: " + playerName + " exists", exception);
        }
    }
}
