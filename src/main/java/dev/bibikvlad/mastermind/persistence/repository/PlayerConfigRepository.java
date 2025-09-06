package dev.bibikvlad.mastermind.persistence.repository;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;

import java.util.Optional;

public interface PlayerConfigRepository {
    Optional<PlayerConfig> findById(int id) throws PersistenceException;

    boolean save(PlayerConfig playerConfig) throws PersistenceException;

    boolean updateLocale(int playerId, LocaleType locale) throws PersistenceException;
}
