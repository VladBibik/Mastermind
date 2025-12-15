package dev.bibikvlad.mastermind.persistence.mappers;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerConfigMapper {
    public static PlayerConfig map(ResultSet resultSet) throws SQLException {
        return new PlayerConfig(LocaleType.valueOf(
                resultSet.getString("language")),
                new LogoColorsBundle(
                        ConsoleColor.valueOf(resultSet.getString("logo_border_color")),
                        ConsoleColor.valueOf(resultSet.getString("logo_main_color")),
                        ConsoleColor.valueOf(resultSet.getString("logo_accent_color")),
                        ConsoleColor.valueOf(resultSet.getString("logo_background_color")))
        );
    }
}
