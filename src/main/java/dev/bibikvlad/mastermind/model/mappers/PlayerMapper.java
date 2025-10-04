package dev.bibikvlad.mastermind.model.mappers;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;
import dev.bibikvlad.utils.formatters.SQLiteTimestampFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerMapper {
    public static Player map(ResultSet resultSet) throws PersistenceException {
        Player mappedPlayer;

        try {
            PlayerConfig playerConfig = PlayerConfigMapper.map(resultSet);

            mappedPlayer = new Player(
                    resultSet.getLong("id"),
                    resultSet.getString("player_name"),
                    SQLiteTimestampFormatter.parse(resultSet.getString("creation_date")),
                    playerConfig
            );
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to parse a player. Please try again later", exception);
        }

        return mappedPlayer;
    }
}
