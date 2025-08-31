package dev.bibikvlad.mastermind.model.mappers;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerConfigMapper {
    public static PlayerConfig map(ResultSet resultSet) throws SQLException {
        return new PlayerConfig(LocaleType.fromLanguageString(
                resultSet.getString("language")),
                ConsoleColor.getByDisplayName("logo_border_color"),
                ConsoleColor.getByDisplayName("logo_main_color"),
                ConsoleColor.getByDisplayName("logo_accent_color"),
                ConsoleColor.getByDisplayName("logo_background_color")
        );
    }
}
