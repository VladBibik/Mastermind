package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.mappers.PlayerConfigMapper;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class JdbcPlayerConfigDAO implements PlayerConfigDAO {
    private final Connection connection;

    public JdbcPlayerConfigDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<PlayerConfig> findById(int id) throws PersistenceException {
        String fetchPlayerConfigQuery = """
                SELECT player_id, language, logo_border_color, logo_main_color, logo_accent_color, logo_background_color
                FROM player_configurations
                WHERE player_id = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(fetchPlayerConfigQuery)) {
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(PlayerConfigMapper.map(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch player configuration by id: " + id, exception);
        }
    }

    @Override
    public boolean save(PlayerConfig playerConfig) throws PersistenceException {
        return false;
    }

    @Override
    public boolean delete(PlayerConfig playerConfig) throws PersistenceException {
        return false;
    }

    @Override
    public boolean update(Player player) throws PersistenceException {
        return false;
    }

    @Override
    public boolean updateLocale(int playerId, LocaleType locale) throws PersistenceException {
        return false;
    }
}
