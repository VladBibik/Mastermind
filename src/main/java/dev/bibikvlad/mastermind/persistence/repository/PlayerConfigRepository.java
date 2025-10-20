package dev.bibikvlad.mastermind.persistence.repository;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;

import java.util.Optional;

public interface PlayerConfigRepository {
    Optional<PlayerConfig> findById(long playerId) throws PersistenceException;

    boolean update(long playerId, PlayerConfig playerConfig) throws PersistenceException;

    boolean updateLocale(long playerId, LocaleType locale) throws PersistenceException;

    boolean updateLogoColors(long playerId, LogoColorsBundle logoColorsBundle) throws PersistenceException;
}
