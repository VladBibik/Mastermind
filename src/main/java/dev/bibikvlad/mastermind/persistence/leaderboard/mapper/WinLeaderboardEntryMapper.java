package dev.bibikvlad.mastermind.persistence.leaderboard.mapper;

import dev.bibikvlad.mastermind.model.leaderboard.WinsLeaderboardEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WinLeaderboardEntryMapper {
    public static WinsLeaderboardEntry map(ResultSet resultSet) throws SQLException {
        return new WinsLeaderboardEntry(
                resultSet.getString("player_name"),
                resultSet.getLong("number_of_wins")
        );
    }
}
