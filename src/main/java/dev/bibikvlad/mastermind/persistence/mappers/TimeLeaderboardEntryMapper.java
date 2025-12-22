package dev.bibikvlad.mastermind.persistence.mappers;

import dev.bibikvlad.mastermind.model.leaderboard.TimeLeaderboardEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TimeLeaderboardEntryMapper {
    public static TimeLeaderboardEntry map(ResultSet resultSet) throws SQLException {
        return new TimeLeaderboardEntry(
                resultSet.getString("player_name"),
                resultSet.getLong("duration_milliseconds")
        );
    }
}
