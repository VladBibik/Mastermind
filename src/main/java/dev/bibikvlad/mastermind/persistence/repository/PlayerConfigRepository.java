package dev.bibikvlad.mastermind.persistence.repository;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;

import java.util.Optional;

public interface PlayerConfigRepository {
    Optional<PlayerConfig> findById(int id);

    void save(PlayerConfig playerConfig);

    void updateLocale(int playerId, LocaleType locale);
}
