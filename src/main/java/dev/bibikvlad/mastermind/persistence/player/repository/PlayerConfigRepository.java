package dev.bibikvlad.mastermind.persistence.player.repository;

import dev.bibikvlad.mastermind.localization.config.LocalizationType;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;

import java.util.Optional;

public interface PlayerConfigRepository {
    Optional<PlayerConfig> findById(long playerId);

    boolean update(long playerId, PlayerConfig playerConfig);

    boolean updateLocalization(long playerId, LocalizationType localizationType);

    boolean updateLogoColors(long playerId, LogoColorsBundle logoColorsBundle);
}
