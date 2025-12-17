package dev.bibikvlad.mastermind.persistence.mappers;

import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.game.data.GameOutcome;
import dev.bibikvlad.mastermind.game.data.GameResult;
import dev.bibikvlad.mastermind.model.game.Game;
import dev.bibikvlad.utils.formatters.SQLiteTimestampFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GameMapper {
    public static Game map(ResultSet resultSet) throws SQLException {
        return new Game(
                resultSet.getLong("game_id"),
                resultSet.getLong("player_id"),
                new GameData(
                        new GameOutcome(
                                resultSet.getInt("number_of_turns"),
                                GameResult.valueOf(resultSet.getString("result"))
                        ),
                        resultSet.getLong("duration_seconds")),
                SQLiteTimestampFormatter.parse(resultSet.getString("started_at")));
    }
}
