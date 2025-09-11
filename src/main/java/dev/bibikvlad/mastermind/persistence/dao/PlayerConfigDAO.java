package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;

import java.util.Optional;

public interface PlayerConfigDAO {
    Optional<PlayerConfig> findById(long playerId) throws PersistenceException;

    boolean save(long playerId, PlayerConfig playerConfig) throws PersistenceException;

    boolean delete(PlayerConfig playerConfig) throws PersistenceException;

    boolean update(Player player) throws PersistenceException;

    boolean updateLocale(long playerId, LocaleType locale) throws PersistenceException;
}
