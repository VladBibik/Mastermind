package dev.bibikvlad.mastermind.persistence.dao.JDBC;

import dev.bibikvlad.mastermind.model.leaderboard.TimeLeaderboardEntry;
import dev.bibikvlad.mastermind.model.leaderboard.TurnsLeaderboardEntry;
import dev.bibikvlad.mastermind.model.leaderboard.WinRateLeaderboardEntry;
import dev.bibikvlad.mastermind.model.leaderboard.WinsLeaderboardEntry;
import dev.bibikvlad.mastermind.persistence.dao.LeaderboardDAO;

import java.util.List;

public class LeaderboardJdbcDAO implements LeaderboardDAO {
    @Override
    public List<TimeLeaderboardEntry> getTimeLeaderboard() {
        return List.of();
    }

    @Override
    public List<TurnsLeaderboardEntry> getTurnsLeaderboard() {
        return List.of();
    }

    @Override
    public List<WinRateLeaderboardEntry> getWinRateLeaderboard() {
        return List.of();
    }

    @Override
    public List<WinsLeaderboardEntry> getWinsLeaderboard() {
        return List.of();
    }
}
