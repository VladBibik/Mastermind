package dev.bibikvlad.mastermind.persistence.leaderboard.mapper;

import dev.bibikvlad.mastermind.model.leaderboard.WinPercentageLeaderboardEntry;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WinPercentageMapper {
    public static WinPercentageLeaderboardEntry map(ResultSet resultSet) throws SQLException {
        return new WinPercentageLeaderboardEntry(
                resultSet.getString("player_name"),
                resultSet.getDouble("win_percentage")
        );
    }
}
