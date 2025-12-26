package dev.bibikvlad.mastermind.persistence.mappers.leaderboards;

import dev.bibikvlad.mastermind.model.leaderboard.MainLeaderboardEntry;
import dev.bibikvlad.utils.formatters.MillisecondsToTimeFormatter;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MainLeaderboardEntryMapper {
    public static MainLeaderboardEntry map(ResultSet resultSet) throws SQLException {
        return new MainLeaderboardEntry(
                resultSet.getString("player_name"),
                resultSet.getInt("number_of_turns"),
                MillisecondsToTimeFormatter.format(resultSet.getLong("duration_milliseconds"))
        );
    }
}
