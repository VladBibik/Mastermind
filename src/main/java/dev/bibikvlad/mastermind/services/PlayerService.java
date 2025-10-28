package dev.bibikvlad.mastermind.services;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;
import dev.bibikvlad.mastermind.persistence.repository.PlayerConfigRepository;
import dev.bibikvlad.mastermind.persistence.repository.PlayerLastSelectedRepository;
import dev.bibikvlad.mastermind.persistence.repository.PlayerRepository;
import dev.bibikvlad.utils.DefaultLogoColorsBundle;

import java.util.List;
import java.util.Optional;

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

    public boolean savePlayerWithProvidedLocale(String newPlayerName, LocaleType locale)
            throws PlayerAlreadyExistException {
        boolean result = false;

        try {
            if (playerRepository.existsByName(newPlayerName)) {
                throw new PlayerAlreadyExistException("Player with name " + newPlayerName + " already exists");
            }

            Player player = new Player(newPlayerName, getCustomLocaleConfig(locale));

            result = playerRepository.save(player);
        } catch (PersistenceException exception) {
            //TODO: Add handling. Preferably just turn off the app
        }

        return result;
    }

    public boolean saveOrUpdateLastSelectedPlayer(long playerId) {
        try {
            return playerLastSelectedRepository.saveOrUpdate(playerId);
        } catch (PersistenceException exception) {
            //TODO: Add handling. Preferably just turn off the app
        }

        throw new IllegalStateException();
    }

    public List<Player> getAllPlayers() {
        try {
            return playerRepository.findAll();
        } catch (PersistenceException exception) {
            //TODO: Add handling. Preferably just turn off the app
        }

        throw new IllegalStateException();
    }

    public Optional<Player> getPlayerByName(String playerName) {
        try {
            return playerRepository.findByName(playerName);
        } catch (PersistenceException exception) {

        }

        throw new IllegalStateException();
    }

    public Optional<Player> loadLastSelectedPlayer() {
        try {
            return playerLastSelectedRepository.getLastSelectedPlayer();
        } catch (PersistenceException exception) {
            //TODO: Add handling. Preferably just turn off the app
        }

        throw new IllegalStateException();
    }

    public void updatePlayerName(long playerId, String newPlayerName) throws PlayerAlreadyExistException {
        try {
            if (playerRepository.existsByName(newPlayerName)) {
                throw new PlayerAlreadyExistException("Player with name " + newPlayerName + " already exists");
            }

            playerRepository.updatePlayerName(playerId, newPlayerName);
        } catch (PersistenceException exception) {
            //TODO: Add handling. Preferably just turn off the app
        }
    }

    public void updatePlayerLocale(long playerId, LocaleType locale) {
        try {
            playerConfigRepository.updateLocale(playerId, locale);
        } catch (PersistenceException exception) {
            //TODO: Add handling. Preferably just turn off the app
        }
    }

    public void updateLogoColors(long playerId, LogoColorsBundle logoColorsBundle) {
        try {
            playerConfigRepository.updateLogoColors(playerId, logoColorsBundle);
        } catch (PersistenceException exception) {
            //TODO: Add handling. Preferably just turn off the app
        }
    }

    public void updateLastSelectedPlayer(long playerId) {
        try {
            playerLastSelectedRepository.saveOrUpdate(playerId);
        } catch (PersistenceException exception) {
            //TODO: Add handling. Preferably just turn off the app
        }
    }

    public void deletePlayer(long playerId) {
        try {
            playerRepository.deleteById(playerId);
        } catch (PersistenceException exception) {
            //TODO: Add handling. Preferably just turn off the app
        }
    }

    public boolean isMultiplePlayersRegistered() {
        try {
            return playerRepository.count() > 1;
        } catch (PersistenceException exception) {
            //TODO: Add handling. Preferably just turn off the app
        }

        throw new IllegalStateException();
    }

    private PlayerConfig getCustomLocaleConfig(LocaleType localeType) {
        return new PlayerConfig(
                localeType,
                DefaultLogoColorsBundle.get()
        );
    }
}
