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
        return Optional.ofNullable(leaderboardRepository.getOverallLeaderboard());
    }

    public Optional<List<TimeLeaderboardEntry>> getTimeLeaderboard(long playerId) {
        return Optional.ofNullable(leaderboardRepository.getTimeLeaderboard());
    }

    public Optional<List<TurnsLeaderboardEntry>> getTurnsLeaderboard(long playerId) {
        return Optional.ofNullable(leaderboardRepository.getTurnsLeaderboard());
    }

    public Optional<List<WinPercentageLeaderboardEntry>> getWinPercentageLeaderboard(long playerId) {
        return Optional.ofNullable(leaderboardRepository.getWinPercentageLeaderboard());
    }

    public Optional<List<WinsLeaderboardEntry>>  getWinsLeaderboard(long playerId) {
        return Optional.ofNullable(leaderboardRepository.getWinsLeaderboard());
    }
}
