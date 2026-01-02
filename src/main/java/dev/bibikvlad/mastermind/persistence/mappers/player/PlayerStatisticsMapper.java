package dev.bibikvlad.mastermind.persistence.mappers.player;

import dev.bibikvlad.mastermind.model.player.PlayerStatistics;
import dev.bibikvlad.utils.formatters.MillisecondsToTimeFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerStatisticsMapper {
    public static PlayerStatistics map(ResultSet resultSet) throws SQLException {
        return new PlayerStatistics(
                resultSet.getLong("game_count"),
                resultSet.getLong("win_count"),
                resultSet.getDouble("win_percentage"),
                MillisecondsToTimeFormatter.format(resultSet.getLong("average_game_duration")),
                MillisecondsToTimeFormatter.format(resultSet.getLong("fastest_win_time")),
                resultSet.getInt("min_turns_win")
        );
    }
}
