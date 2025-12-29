package dev.bibikvlad.mastermind.persistence.repository.SQL;

import dev.bibikvlad.mastermind.model.leaderboard.*;
import dev.bibikvlad.mastermind.persistence.repository.LeaderboardRepository;

import java.util.List;

public class LeaderboardSQLRepository implements LeaderboardRepository {
    @Override
    public List<MainLeaderboardEntry> getMainLeaderboard() {
        return List.of();
    }

    @Override
    public List<TimeLeaderboardEntry> getTimeLeaderboard() {
        return List.of();
    }

    @Override
    public List<TurnsLeaderboardEntry> getTurnsLeaderboard() {
        return List.of();
    }

    @Override
    public List<WinPercentageLeaderboardEntry> getWinRateLeaderboard() {
        return List.of();
    }

    @Override
    public List<WinsLeaderboardEntry> getWinsLeaderboard() {
        return List.of();
    }
}
