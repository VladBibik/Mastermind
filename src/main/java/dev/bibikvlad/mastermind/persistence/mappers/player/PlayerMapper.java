package dev.bibikvlad.mastermind.persistence.mappers.player;

import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;
import dev.bibikvlad.utils.formatters.SQLiteTimestampFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerMapper {
    public static Player map(ResultSet resultSet) throws SQLException {
        PlayerConfig playerConfig = PlayerConfigMapper.map(resultSet);

        return new Player(
                resultSet.getLong("player_id"),
                resultSet.getString("player_name"),
                SQLiteTimestampFormatter.parse(resultSet.getString("created_at")),
                playerConfig
        );
    }
}
