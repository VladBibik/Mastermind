package dev.bibikvlad.mastermind.persistence.leaderboard.mapper;

import dev.bibikvlad.mastermind.persistence.leaderboard.model.TurnsLeaderboardEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TurnsLeaderboardEntryMapper {
    public static TurnsLeaderboardEntry map(ResultSet resultSet) throws SQLException {
        return new TurnsLeaderboardEntry(
                resultSet.getString("player_name"),
                resultSet.getInt("number_of_turns")
        );
    }
}
