package dev.bibikvlad.mastermind.persistence.player.repository;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.persistence.player.model.PlayerConfig;

import java.util.Optional;

public interface PlayerConfigRepository {
    Optional<PlayerConfig> findById(long playerId);

    boolean update(long playerId, PlayerConfig playerConfig);

    boolean updateLocale(long playerId, LocaleType locale);

    boolean updateLogoColors(long playerId, LogoColorsBundle logoColorsBundle);
}
