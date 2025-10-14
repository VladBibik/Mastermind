package dev.bibikvlad.mastermind.services;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;
import dev.bibikvlad.mastermind.persistence.repository.PlayerConfigRepository;
import dev.bibikvlad.mastermind.persistence.repository.PlayerRepository;

import java.util.List;

public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerConfigRepository playerConfigRepository;

    public PlayerService(PlayerRepository playerRepository, PlayerConfigRepository playerConfigRepository) {
        this.playerRepository = playerRepository;
        this.playerConfigRepository = playerConfigRepository;
    }

    public void savePlayerWithDefaultConfigs(String newPlayerName) throws PlayerAlreadyExistException {
        try {
            if (playerRepository.existsByName(newPlayerName)) {
                throw new PlayerAlreadyExistException("Player with name " + newPlayerName + " already exists");
            }

            Player player = new Player(newPlayerName, getDefaultPlayerConfig());

            playerRepository.save(player);
        } catch (PersistenceException exception) {
            //TODO: Add handling. Preferably just turn off the app
        }
    }

    public List<Player> getAllPlayers() {
        try {
            return playerRepository.findAll();
        } catch (PersistenceException exception) {
            //TODO: Add handling. Preferably just turn off the app
        }

        throw new IllegalStateException();
    }

    private PlayerConfig getDefaultPlayerConfig() {
        return new PlayerConfig(LocaleType.ENGLISH,
                ConsoleColor.ORCHID,
                ConsoleColor.ORANGE,
                ConsoleColor.BRIGHT_RED,
                ConsoleColor.BACKGROUND_BLACK);
    }
}
