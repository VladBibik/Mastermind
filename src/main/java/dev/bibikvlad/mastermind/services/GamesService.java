package dev.bibikvlad.mastermind.services;

import dev.bibikvlad.mastermind.persistence.repository.GamesRepository;

public class GamesService {
    private final GamesRepository gamesRepository;

    public GamesService(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }
}
