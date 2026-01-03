package dev.bibikvlad.mastermind.persistence.leaderboard.mapper;

import dev.bibikvlad.mastermind.persistence.leaderboard.model.TimeLeaderboardEntry;
import dev.bibikvlad.utils.formatters.MillisecondsToTimeFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeLeaderboardEntryMapper {
    public static TimeLeaderboardEntry map(ResultSet resultSet) throws SQLException {
        return new TimeLeaderboardEntry(
                resultSet.getString("player_name"),
                MillisecondsToTimeFormatter.format(resultSet.getLong("duration_milliseconds"))
        );
    }
}
