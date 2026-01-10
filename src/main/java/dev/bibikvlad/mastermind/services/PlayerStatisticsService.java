package dev.bibikvlad.mastermind.services;

import dev.bibikvlad.mastermind.persistence.player.repository.PlayerStatisticsRepository;

public class PlayerStatisticsService {
    private final PlayerStatisticsRepository playerStatisticsRepository;

    public PlayerStatisticsService(PlayerStatisticsRepository playerStatisticsRepository) {
        this.playerStatisticsRepository = playerStatisticsRepository;
    }
}
