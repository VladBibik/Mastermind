package dev.bibikvlad.mastermind.services;

import dev.bibikvlad.mastermind.persistence.leaderboard.repository.LeaderboardRepository;

public class LeaderboardService {
    private final LeaderboardRepository leaderboardRepository;

    public LeaderboardService(LeaderboardRepository leaderboardRepository) {
        this.leaderboardRepository = leaderboardRepository;
    }
}
