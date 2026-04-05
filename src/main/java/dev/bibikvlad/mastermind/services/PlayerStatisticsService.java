package dev.bibikvlad.mastermind.services;

import dev.bibikvlad.mastermind.model.player.PlayerStatistics;
import dev.bibikvlad.mastermind.persistence.player.repository.PlayerStatisticsRepository;

import java.util.Optional;

public class PlayerStatisticsService {
    private final PlayerStatisticsRepository playerStatisticsRepository;

    public PlayerStatisticsService(PlayerStatisticsRepository playerStatisticsRepository) {
        this.playerStatisticsRepository = playerStatisticsRepository;
    }

    public Optional<PlayerStatistics> getPlayerStatistics(long playerId) {
        return Optional.ofNullable(playerStatisticsRepository.getPlayerStatistics(playerId));
    }
}
