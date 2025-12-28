package dev.bibikvlad.mastermind.persistence.repository;

import dev.bibikvlad.mastermind.model.leaderboard.*;

import java.util.List;

public interface LeaderboardRepository {
    List<MainLeaderboardEntry> getMainLeaderboard();

    List<TimeLeaderboardEntry> getTimeLeaderboard();

    List<TurnsLeaderboardEntry> getTurnsLeaderboard();

    List<WinPercentageLeaderboardEntry> getWinRateLeaderboard();

    List<WinsLeaderboardEntry> getWinsLeaderboard();
}
