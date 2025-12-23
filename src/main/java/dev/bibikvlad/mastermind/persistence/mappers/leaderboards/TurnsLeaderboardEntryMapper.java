package dev.bibikvlad.mastermind.persistence.mappers.leaderboards;

import dev.bibikvlad.mastermind.model.leaderboard.TurnsLeaderboardEntry;

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
