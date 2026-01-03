package dev.bibikvlad.mastermind.persistence.leaderboard.dao;

import dev.bibikvlad.mastermind.model.leaderboard.*;

import java.util.List;

public interface LeaderboardDAO {
    List<MainLeaderboardEntry> getOverallLeaderboard();

    List<TimeLeaderboardEntry> getTimeLeaderboard();

    List<TurnsLeaderboardEntry> getTurnsLeaderboard();

    List<WinPercentageLeaderboardEntry> getWinPercentageLeaderboard();

    List<WinsLeaderboardEntry> getWinsLeaderboard();
}
