package dev.bibikvlad.mastermind.persistence.dao.JDBC;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.mappers.PlayerMapper;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.persistence.dao.PlayerLastSelectedDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerLastSelectedJdbcDAO implements PlayerLastSelectedDAO {
    private final Connection connection;

    public PlayerLastSelectedJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean saveOrUpdate(long id) throws PersistenceException {
        String saveOrUpdateQuery = """
                INSERT INTO player_last_selected (player_id, last_selected_at)
                VALUES (?, CURRENT_TIMESTAMP)
                ON CONFLICT(player_id)
                DO UPDATE SET last_selected_at = excluded.last_selected_at;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(saveOrUpdateQuery)) {
            preparedStatement.setLong(1, id);

            return preparedStatement.execute();
        } catch (SQLException exception) {
            throw new PersistenceException(exception.getMessage());
        }
    }

    @Override
    public long getLastSelectedPlayerId() throws PersistenceException {
        String getLastSelectedQuery= "SELECT MAX(last_selected_at) FROM player_last_selected";

        try (PreparedStatement preparedStatement = connection.prepareStatement(getLastSelectedQuery)) {
            preparedStatement.executeQuery();

            return preparedStatement.getResultSet().getLong("player_id");
        } catch (SQLException exception) {
            throw new PersistenceException(exception.getMessage());
        }
    }

    @Override
    public Player getLastSelectedPlayer() throws PersistenceException {
        String getLastSelectedPlayerQuery= """
                SELECT PLAYER.id, PLAYER.player_name, PLAYER.creation_date,
                       CONF.language, CONF.logo_border_color, CONF.logo_main_color,
                       CONF.logo_accent_color, CONF.logo_background_color
                FROM player_last_selected LAST
                LEFT JOIN players PLAYER ON LAST.player_id = PLAYER.id
                LEFT JOIN player_configurations CONF ON LAST.player_id = CONF.player_id
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(getLastSelectedPlayerQuery)) {
            preparedStatement.executeQuery();

            return PlayerMapper.map(preparedStatement.getResultSet());
        } catch (SQLException exception) {
            throw new PersistenceException(exception.getMessage());
        }
    }
}
