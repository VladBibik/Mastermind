package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.model.leaderboard.*;

import java.util.List;

public interface LeaderboardDAO {
    List<MainLeaderboardEntry> getMainLeaderboard();

    List<TimeLeaderboardEntry> getTimeLeaderboard();

    List<TurnsLeaderboardEntry> getTurnsLeaderboard();

    List<WinPercentageLeaderboardEntry> getWinRateLeaderboard();

    List<WinsLeaderboardEntry> getWinsLeaderboard();
}
