package dev.bibikvlad.mastermind.services;

import dev.bibikvlad.mastermind.persistence.leaderboard.model.*;
import dev.bibikvlad.mastermind.persistence.leaderboard.repository.LeaderboardRepository;

import java.util.List;
import java.util.Optional;

public class LeaderboardService {
    private final LeaderboardRepository leaderboardRepository;

    public LeaderboardService(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }

    public Optional<List<MainLeaderboardEntry>> getMainLeaderboard(long playerId) {
        List<MainLeaderboardEntry> mainLeaderboardEntries = leaderboardRepository.getOverallLeaderboard();

        if (mainLeaderboardEntries.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(mainLeaderboardEntries);
        }
    }

    public Optional<List<TimeLeaderboardEntry>> getTimeLeaderboard(long playerId) {
        List<TimeLeaderboardEntry> timeLeaderboardEntries = leaderboardRepository.getTimeLeaderboard();

        if (timeLeaderboardEntries.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(timeLeaderboardEntries);
        }
    }

    public Optional<List<TurnsLeaderboardEntry>> getTurnsLeaderboard(long playerId) {
        List<TurnsLeaderboardEntry> turnsLeaderboardEntries = leaderboardRepository.getTurnsLeaderboard();

        if (turnsLeaderboardEntries.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(turnsLeaderboardEntries);
        }
    }

    public Optional<List<WinPercentageLeaderboardEntry>> getWinPercentageLeaderboard(long playerId,
                                                                                     int minGamesPlayed) {
        List<WinPercentageLeaderboardEntry> winPercentageLeaderboardEntries =
                leaderboardRepository.getWinPercentageLeaderboard(minGamesPlayed);

        if (winPercentageLeaderboardEntries.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(winPercentageLeaderboardEntries);
        }
    }

    public Optional<List<WinsLeaderboardEntry>> getWinsLeaderboard(long playerId) {
        List<WinsLeaderboardEntry> winsLeaderboardEntries = leaderboardRepository.getWinsLeaderboard();

        if (winsLeaderboardEntries.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(winsLeaderboardEntries);
        }
    }
}
