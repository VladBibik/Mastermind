package dev.bibikvlad.mastermind.services;

import dev.bibikvlad.mastermind.persistence.repository.PlayerConfigRepository;
import dev.bibikvlad.mastermind.persistence.repository.PlayerRepository;

public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerConfigRepository playerConfigRepository;

    public PlayerService(PlayerRepository playerRepository, PlayerConfigRepository playerConfigRepository) {
        this.playerRepository = playerRepository;
        this.playerConfigRepository = playerConfigRepository;
    }
}
