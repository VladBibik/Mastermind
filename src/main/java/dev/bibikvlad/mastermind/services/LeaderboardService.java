package dev.bibikvlad.mastermind.services;

import dev.bibikvlad.mastermind.persistence.leaderboard.model.MainLeaderboardEntry;
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
}
