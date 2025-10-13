package dev.bibikvlad.mastermind.services;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.persistence.repository.PlayerConfigRepository;
import dev.bibikvlad.mastermind.persistence.repository.PlayerRepository;

public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerConfigRepository playerConfigRepository;

    public PlayerService(PlayerRepository playerRepository, PlayerConfigRepository playerConfigRepository) {
        this.playerRepository = playerRepository;
        this.playerConfigRepository = playerConfigRepository;
    }

    public void savePlayer(Player player) throws PlayerAlreadyExistException {
        try {
            if (playerRepository.existsByName(player.getPlayerName())) {
                throw new PlayerAlreadyExistException("Player with name " + player.getPlayerName() + " already exists");
            }

            playerRepository.save(player);
        } catch (PersistenceException exception) {
            //TODO: Add handling. Preferably just turn off the app
        }
    }
}
