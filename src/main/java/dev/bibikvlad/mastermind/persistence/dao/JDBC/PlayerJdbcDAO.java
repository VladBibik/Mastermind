package dev.bibikvlad.mastermind.persistence.dao.JDBC;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.persistence.mappers.player.PlayerMapper;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;
import dev.bibikvlad.mastermind.persistence.dao.PlayerDAO;

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
    public List<Player> findAll() {
        List<Player> players = new ArrayList<>();
        String fetchAllPlayersQuery = """
                SELECT PLAYER.player_id, player_name, created_at, language, logo_border_color,
                       logo_main_color, logo_accent_color, logo_background_color
                FROM players PLAYER
                LEFT JOIN player_configurations CONF
                ON PLAYER.player_id = CONF.player_id
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
    public Optional<Player> findById(long playerId) {
        String fetchPlayerQuery = """
                        SELECT PLAYER.player_id, player_name, created_at, language, logo_border_color,
                               logo_main_color, logo_accent_color, logo_background_color
                        FROM players PLAYER
                        LEFT JOIN player_configurations CONF
                        ON PLAYER.player_id = CONF.player_id
                        WHERE PLAYER.player_id = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(fetchPlayerQuery)) {
            preparedStatement.setLong(1, playerId);
            return getPlayer(preparedStatement);
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch player by ID: " + playerId, exception);
        }
    }

    @Override
    public Optional<Player> findByName(String playerName) {
        String fetchPlayerQuery = """
                        SELECT PLAYER.player_id, player_name, created_at, language, logo_border_color,
                               logo_main_color, logo_accent_color, logo_background_color
                        FROM players PLAYER
                        LEFT JOIN player_configurations CONF
                        ON PLAYER.player_id = CONF.player_id
                        WHERE PLAYER.player_name = ?
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
    public boolean save(Player player) {
        String addPlayerQuery = "INSERT INTO players (player_name) VALUES (?)";
        String addPlayerConfigQuery = "INSERT INTO player_configurations (player_id, language, logo_border_color,  " +
                "logo_main_color, logo_accent_color, logo_background_color) VALUES (?, ?, ?, ?, ?, ?)";
        String addLastPlayerSelectedQuery = "INSERT INTO player_last_selected (player_id) VALUES (?)";
        int rowsUpdated;

        try (PreparedStatement playerPreparedStatement =
                     connection.prepareStatement(addPlayerQuery, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement configPreparedStatement = connection.prepareStatement(addPlayerConfigQuery);
             PreparedStatement lastSelectedStatement = connection.prepareStatement(addLastPlayerSelectedQuery)) {


            playerPreparedStatement.setString(1, player.getPlayerName());
            playerPreparedStatement.executeUpdate();

            try (ResultSet generatedKeys = playerPreparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    long playerId = generatedKeys.getLong(1);
                    PlayerConfig playerConfig = player.getPlayerConfig();
                    LogoColorsBundle logoColorsBundle = playerConfig.logoColorsBundle();

                    configPreparedStatement.setLong(1, playerId);
                    configPreparedStatement.setString(2, playerConfig.locale().name());
                    configPreparedStatement.setString(3, logoColorsBundle
                            .logoBorderColor().name());
                    configPreparedStatement.setString(4, logoColorsBundle
                            .logoMainColor().name());
                    configPreparedStatement.setString(5, logoColorsBundle
                            .logoAccentColor().name());
                    configPreparedStatement.setString(6, logoColorsBundle
                            .logoBackgroundColor().name());
                    rowsUpdated = configPreparedStatement.executeUpdate();

                    lastSelectedStatement.setLong(1, playerId);
                    lastSelectedStatement.executeUpdate();
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
    public boolean update(Player player) {
        String updatePlayerQuery = """
                        UPDATE players
                        SET player_name = ?
                        WHERE player_id = ?;
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

            PlayerConfig playerConfig = player.getPlayerConfig();
            LogoColorsBundle logoColorsBundle = playerConfig.logoColorsBundle();

            configPreparedStatement.setString(1, playerConfig.locale().name());
            configPreparedStatement.setString(2, logoColorsBundle.logoBorderColor().name());
            configPreparedStatement.setString(3, logoColorsBundle.logoMainColor().name());
            configPreparedStatement.setString(4, logoColorsBundle.logoAccentColor().name());
            configPreparedStatement.setString(5, logoColorsBundle.logoBackgroundColor().name());
            configPreparedStatement.setLong(6, player.getId());
            rowsUpdated = configPreparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to update a Player: " + player, exception);
        }

        return rowsUpdated > 0;
    }

    @Override
    public boolean updatePlayerName(long id, String name) {
        String updatePlayerNameQuery = """
                        UPDATE players
                        SET player_name = ?
                        WHERE player_id = ?;
        """;

        int rowsUpdated;

        try (PreparedStatement preparedStatement = connection.prepareStatement(updatePlayerNameQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setLong(2, id);
            rowsUpdated = preparedStatement.executeUpdate();

            return  rowsUpdated > 0;
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to update a Player name: " + name, exception);
        }
    }

    @Override
    public boolean delete(Player player) {
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
    public boolean deleteById(long playerId) {
        String deletePlayerQuery = """
                        DELETE FROM players
                        WHERE player_id = ?;
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
    public boolean deleteByName(String playerName) {
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
    public boolean existsById(long playerId) {
        String playerQuery = """
                            SELECT 1 FROM players
                            WHERE player_id = ?;
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
    public boolean existsByName(String playerName) {
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

    @Override
    public int count() {
        String countQuery = "SELECT COUNT(player_id) FROM players";

        try (PreparedStatement preparedStatement = connection.prepareStatement(countQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.getInt(1);
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to check if player count", exception);
        }
    }
}
