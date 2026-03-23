package dev.bibikvlad.mastermind.persistence.leaderboard.dao;

import dev.bibikvlad.mastermind.persistence.leaderboard.model.*;

import java.util.List;

public interface LeaderboardDAO {
    List<MainLeaderboardEntry> getOverallLeaderboard();

    List<TimeLeaderboardEntry> getTimeLeaderboard();

    List<TurnsLeaderboardEntry> getTurnsLeaderboard();

    List<WinPercentageLeaderboardEntry> getWinPercentageLeaderboard(int minGamesPlayed);

    List<WinsLeaderboardEntry> getWinsLeaderboard();
}
