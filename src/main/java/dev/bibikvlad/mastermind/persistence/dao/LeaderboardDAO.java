package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.model.leaderboard.TimeLeaderboardEntry;
import dev.bibikvlad.mastermind.model.leaderboard.TurnsLeaderboardEntry;
import dev.bibikvlad.mastermind.model.leaderboard.WinPercentageLeaderboardEntry;
import dev.bibikvlad.mastermind.model.leaderboard.WinRateLeaderboardEntry;
import dev.bibikvlad.mastermind.model.leaderboard.WinsLeaderboardEntry;

import java.util.List;

public interface LeaderboardDAO {
    List<TimeLeaderboardEntry> getTimeLeaderboard();

    List<TurnsLeaderboardEntry> getTurnsLeaderboard();

    List<WinPercentageLeaderboardEntry> getWinRateLeaderboard();

    List<WinsLeaderboardEntry> getWinsLeaderboard();
}
