package dev.bibikvlad.mastermind.services;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.exceptions.PlayerNotFoundException;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;
import dev.bibikvlad.mastermind.persistence.repository.PlayerConfigRepository;
import dev.bibikvlad.mastermind.persistence.repository.PlayerLastSelectedRepository;
import dev.bibikvlad.mastermind.persistence.repository.PlayerRepository;

import java.util.List;

public class PlayerService {
    private final PlayerRepository playerRepository;
    private final PlayerConfigRepository playerConfigRepository;
    private final PlayerLastSelectedRepository playerLastSelectedRepository;

    public PlayerService(PlayerRepository playerRepository, PlayerConfigRepository playerConfigRepository,
                         PlayerLastSelectedRepository playerLastSelectedRepository) {
        this.playerRepository = playerRepository;
        this.playerConfigRepository = playerConfigRepository;
        this.playerLastSelectedRepository = playerLastSelectedRepository;
    }

    public boolean savePlayerWithDefaultConfigs(String newPlayerName) throws PlayerAlreadyExistException {
        boolean result = false;

        try {
            if (playerRepository.existsByName(newPlayerName)) {
                throw new PlayerAlreadyExistException("Player with name " + newPlayerName + " already exists");
            }

            Player player = new Player(newPlayerName, getDefaultPlayerConfig());

            result = playerRepository.save(player);
        } catch (PersistenceException exception) {
            //TODO: Add handling. Preferably just turn off the app
        }

        return result;
    }

    public List<Player> getAllPlayers() {
        try {
            return playerRepository.findAll();
        } catch (PersistenceException exception) {
            //TODO: Add handling. Preferably just turn off the app
        }

        throw new IllegalStateException();
    }

    public Player loadLastSelectedPlayer() throws PlayerNotFoundException {
        try {
            Player player = playerLastSelectedRepository.getLastSelectedPlayer();

            if (player == null) {
                throw new PlayerNotFoundException("Player with name " + player.getPlayerName() + " not found");
            }

            return player;
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
