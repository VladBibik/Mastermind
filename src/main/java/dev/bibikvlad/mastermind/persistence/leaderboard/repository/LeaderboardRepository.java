package dev.bibikvlad.mastermind.persistence.leaderboard.repository;

import dev.bibikvlad.mastermind.persistence.leaderboard.model.*;

import java.util.List;

public interface LeaderboardRepository {
    List<MainLeaderboardEntry> getOverallLeaderboard();

    List<TimeLeaderboardEntry> getTimeLeaderboard();

    List<TurnsLeaderboardEntry> getTurnsLeaderboard();

    List<WinPercentageLeaderboardEntry> getWinPercentageLeaderboard();

    List<WinsLeaderboardEntry> getWinsLeaderboard();
}
