package dev.bibikvlad.mastermind.persistence.dao.JDBC;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.persistence.mappers.PlayerConfigMapper;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;
import dev.bibikvlad.mastermind.persistence.dao.PlayerConfigDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class PlayerConfigJdbcDAO implements PlayerConfigDAO {
    private final Connection connection;

    public PlayerConfigJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<PlayerConfig> findById(long playerId) {
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
    public boolean update(long playerId, PlayerConfig playerConfig) {
        String updateConfigQuery = """
                        UPDATE player_configurations
                        SET language = ?, logo_border_color = ?,
                            logo_main_color = ?, logo_accent_color = ?, logo_background_color = ?
                        WHERE player_id = ?;
                """;
        int rowsUpdated;

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateConfigQuery)) {
            LogoColorsBundle logoColorsBundle = playerConfig.logoColorsBundle();

            preparedStatement.setString(1, playerConfig.locale().name());
            preparedStatement.setString(2, logoColorsBundle.getLogoBorderColor().name());
            preparedStatement.setString(3, logoColorsBundle.getLogoMainColor().name());
            preparedStatement.setString(4, logoColorsBundle.getLogoAccentColor().name());
            preparedStatement.setString(5, logoColorsBundle.getLogoBackgroundColor().name());
            preparedStatement.setLong(6, playerId);
            rowsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to update a Player Configurations for a Player with ID: "
                    + playerId, exception);
        }

        return rowsUpdated > 0;
    }

    @Override
    public boolean updateLocale(long playerId, LocaleType locale) {
        String localeUpdateQuery = """
                UPDATE player_configurations
                SET language = ?
                WHERE player_id = ?;
                """;
        int rowsUpdated;

        try (PreparedStatement preparedStatement = connection.prepareStatement(localeUpdateQuery)) {
            preparedStatement.setString(1, locale.name());
            preparedStatement.setLong(2, playerId);
            rowsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to update Locale in Player Configurations for a Player with ID: "
                    + playerId, exception);
        }

        return rowsUpdated > 0;
    }

    @Override
    public boolean updateLogoColors(long playerId, LogoColorsBundle logoColorsBundle) {
        String logoColorUpdateQuery = """
                UPDATE player_configurations
                SET logo_border_color = ?, logo_main_color = ?, logo_accent_color = ?, logo_background_color = ?
                WHERE player_id = ?;
                """;
        int rowsUpdated;

        try (PreparedStatement preparedStatement = connection.prepareStatement(logoColorUpdateQuery)) {
            preparedStatement.setString(1, logoColorsBundle.getLogoBorderColor().name());
            preparedStatement.setString(2, logoColorsBundle.getLogoMainColor().name());
            preparedStatement.setString(3, logoColorsBundle.getLogoAccentColor().name());
            preparedStatement.setString(4, logoColorsBundle.getLogoBackgroundColor().name());
            preparedStatement.setLong(5, playerId);
            rowsUpdated = preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to update Logo Color in Player Configurations for a Player with ID: "
                    + playerId, exception);
        }

        return rowsUpdated > 0;
    }
}
