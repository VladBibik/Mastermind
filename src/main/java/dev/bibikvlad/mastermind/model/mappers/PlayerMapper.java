package dev.bibikvlad.mastermind.model.mappers;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;
import dev.bibikvlad.utils.formatters.SQLiteTimestampFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerMapper {
    public static Player map(ResultSet resultSet) throws SQLException {
        PlayerConfig playerConfig = new PlayerConfig(LocaleType.fromLanguageString(
                resultSet.getString("language")));

        return new Player(
                resultSet.getLong("id"),
                resultSet.getString("player_name"),
                SQLiteTimestampFormatter.parse(resultSet.getString("creation_date")),
                playerConfig
        );
    }
}
