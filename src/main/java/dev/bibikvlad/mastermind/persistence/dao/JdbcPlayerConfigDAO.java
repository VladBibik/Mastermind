package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.mappers.PlayerConfigMapper;
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
    public Optional<PlayerConfig> findById(long playerId) throws PersistenceException {
        String fetchPlayerConfigQuery = """
                SELECT player_id, language, logo_border_color, logo_main_color, logo_accent_color, logo_background_color
                FROM player_configurations
                WHERE player_id = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(fetchPlayerConfigQuery)) {
            preparedStatement.setLong(1, playerId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return Optional.of(PlayerConfigMapper.map(resultSet));
            } else {
                return Optional.empty();
            }
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch player configuration by id: " + playerId, exception);
        }
    }

    @Override
    public boolean update(long playerId, PlayerConfig playerConfig) throws PersistenceException {
        String updateConfigQuery = """
                        UPDATE player_configurations
                        SET language = ?, logo_border_color = ?,
                            logo_main_color = ?, logo_accent_color = ?, logo_background_color = ?
                        WHERE player_id = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateConfigQuery)) {
            preparedStatement.setString(1, playerConfig.getLocale().getLanguageName());
            preparedStatement.setString(2, playerConfig.getLogoBorderColor().getDisplayName());
            preparedStatement.setString(3, playerConfig.getLogoMainColor().getDisplayName());
            preparedStatement.setString(4, playerConfig.getLogoAccentColor().getDisplayName());
            preparedStatement.setString(5, playerConfig.getLogoBackgroundColor().getDisplayName());
            preparedStatement.setLong(6, playerId);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to update a Player Configurations for a Player with ID: "
                    + playerId, exception);
        }

        return true;
    }

    @Override
    public boolean updateLocale(long playerId, LocaleType locale) throws PersistenceException {
        String localeUpdateQuery = """
                UPDATE player_configurations
                SET language = ?
                WHERE player_id = ?;
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(localeUpdateQuery)) {
            preparedStatement.setString(1, locale.getLanguageName());
            preparedStatement.setLong(2, playerId);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to update Locale in Player Configurations for a Player with ID: "
                    + playerId, exception);
        }

        return true;
    }

    @Override
    public boolean updateLogoColors(long playerId, PlayerConfig playerConfig) throws PersistenceException {
        String updateConfigQuery = """
                        UPDATE player_configurations
                        SET logo_border_color = ?, logo_main_color = ?,
                            logo_accent_color = ?, logo_background_color = ?
                        WHERE player_id = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateConfigQuery)) {
            preparedStatement.setString(1, playerConfig.getLogoBorderColor().getDisplayName());
            preparedStatement.setString(2, playerConfig.getLogoMainColor().getDisplayName());
            preparedStatement.setString(3, playerConfig.getLogoAccentColor().getDisplayName());
            preparedStatement.setString(4, playerConfig.getLogoBackgroundColor().getDisplayName());
            preparedStatement.setLong(5, playerId);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to update Logo Colors for a Player with ID: "
                    + playerId, exception);
        }

        return true;
    }
}
