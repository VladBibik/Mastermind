package dev.bibikvlad.mastermind.services;

import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.persistence.repository.GamesRepository;

public class GamesService {
    private final GamesRepository gamesRepository;

    public GamesService(GamesRepository gamesRepository) {
        this.gamesRepository = gamesRepository;
    }

    public boolean save(long playerId, GameData gameData) {
        return gamesRepository.save(playerId, gameData);
    }
}
