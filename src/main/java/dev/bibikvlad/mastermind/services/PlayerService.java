package dev.bibikvlad.mastermind.services;

import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.localization.config.LocalizationType;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;
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

    public Player createPlayer(String newPlayerName, LocalizationType locale)
            throws PlayerAlreadyExistException {
        if (playerRepository.existsByName(newPlayerName)) {
            throw new PlayerAlreadyExistException("Player with name " + newPlayerName + " already exists");
        }

        Player player = new Player(newPlayerName, getCustomLocaleConfig(locale));

        return playerRepository.save(player);
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
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

    public void updatePlayerLocale(long playerId, LocalizationType locale) {
        playerConfigRepository.updateLocalization(playerId, locale);
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

    public LocalizationType turnCompatibilityOn(long playerId) {
        LocalizationType compatibilityLocalization = LocalizationType.COMPATIBILITY;

        playerConfigRepository.updateLocalization(playerId, compatibilityLocalization);

        return compatibilityLocalization;
    }

    public LocalizationType turnCompatibilityOff(long playerId) {
        LocalizationType englishLocalization = LocalizationType.ENGLISH;

        playerConfigRepository.updateLocalization(playerId, englishLocalization);

        return englishLocalization;
    }

    public boolean isMultiplePlayersRegistered() {
        return playerRepository.count() > 1;
    }

    public boolean isInCompatibilityMode(LocalizationType localizationType) {
        return LocalizationType.COMPATIBILITY.equals(localizationType);
    }

    private PlayerConfig getCustomLocaleConfig(LocalizationType localizationType) {
        return new PlayerConfig(
                localizationType,
                DefaultLogoColorsBundle.INSTANCE
        );
    }
}
