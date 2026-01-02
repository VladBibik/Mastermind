package dev.bibikvlad.mastermind.persistence.dao.JDBC;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.persistence.mappers.player.PlayerMapper;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.persistence.dao.PlayerLastSelectedDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PlayerLastSelectedJdbcDAO implements PlayerLastSelectedDAO {
    private final Connection connection;

    public PlayerLastSelectedJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public long getLastSelectedPlayerId() {
        String getLastSelectedQuery = """
                SELECT player_id
                FROM player_last_selected
                ORDER BY last_selected_at DESC
                LIMIT 1;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getLastSelectedQuery)) {
            preparedStatement.executeQuery();

            return preparedStatement.getResultSet().getLong("player_id");
        } catch (SQLException exception) {
            throw new PersistenceException(exception.getMessage());
        }
    }

    @Override
    public Optional<Player> getLastSelectedPlayer() {
        String getLastSelectedPlayerQuery = """
                SELECT PLAYER.player_id, PLAYER.player_name, PLAYER.created_at,
                       CONF.language, CONF.logo_border_color, CONF.logo_main_color,
                       CONF.logo_accent_color, CONF.logo_background_color
                FROM player_last_selected LAST
                LEFT JOIN players PLAYER ON LAST.player_id = PLAYER.player_id
                LEFT JOIN player_configurations CONF ON LAST.player_id = CONF.player_id
                ORDER BY LAST.last_selected_at DESC
                LIMIT 1;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getLastSelectedPlayerQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(PlayerMapper.map(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException exception) {
            throw new PersistenceException(exception.getMessage());
        }
    }

    @Override
    public boolean saveOrUpdate(long playerId) {
        String saveOrUpdateQuery = """
                INSERT INTO player_last_selected (player_id, last_selected_at)
                VALUES (?, CURRENT_TIMESTAMP)
                ON CONFLICT(player_id)
                DO UPDATE SET last_selected_at = excluded.last_selected_at;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(saveOrUpdateQuery)) {
            preparedStatement.setLong(1, playerId);

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException exception) {
            throw new PersistenceException(exception.getMessage());
        }
    }
}
