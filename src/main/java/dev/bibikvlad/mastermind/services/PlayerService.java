package dev.bibikvlad.mastermind.services;

import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.persistence.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.persistence.player.model.PlayerConfig;
import dev.bibikvlad.mastermind.persistence.player.repository.PlayerConfigRepository;
import dev.bibikvlad.mastermind.persistence.player.repository.PlayerLastSelectedRepository;
import dev.bibikvlad.mastermind.persistence.player.repository.PlayerRepository;
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
        if (playerRepository.existsByName(newPlayerName)) {
            throw new PlayerAlreadyExistException("Player with name " + newPlayerName + " already exists");
        }

        Player player = new Player(newPlayerName, getCustomLocaleConfig(locale));

        return playerRepository.save(player);
    }

    public boolean saveOrUpdateLastSelectedPlayer(long playerId) {
        return playerLastSelectedRepository.saveOrUpdate(playerId);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Optional<Player> getPlayerByName(String playerName) {
        return playerRepository.findByName(playerName);
    }

    public Optional<Player> loadLastSelectedPlayer() {
        return playerLastSelectedRepository.getLastSelectedPlayer();
    }

    public void updatePlayerName(long playerId, String newPlayerName) throws PlayerAlreadyExistException {
        if (playerRepository.existsByName(newPlayerName)) {
            throw new PlayerAlreadyExistException("Player with name " + newPlayerName + " already exists");
        }

        playerRepository.updatePlayerName(playerId, newPlayerName);
    }

    public void updatePlayerLocale(long playerId, LocaleType locale) {
        playerConfigRepository.updateLocale(playerId, locale);
    }

    public void updateLogoColors(long playerId, LogoColorsBundle logoColorsBundle) {
        playerConfigRepository.updateLogoColors(playerId, logoColorsBundle);
    }

    public void updateLastSelectedPlayer(long playerId) {
        playerLastSelectedRepository.saveOrUpdate(playerId);
    }

    public void deletePlayer(long playerId) {
        playerRepository.deleteById(playerId);
    }

    public boolean isMultiplePlayersRegistered() {
        return playerRepository.count() > 1;
    }

    private PlayerConfig getCustomLocaleConfig(LocaleType localeType) {
        return new PlayerConfig(
                localeType,
                DefaultLogoColorsBundle.get()
        );
    }
}
