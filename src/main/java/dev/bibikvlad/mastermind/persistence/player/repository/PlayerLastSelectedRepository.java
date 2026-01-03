package dev.bibikvlad.mastermind.persistence.player.repository;

import dev.bibikvlad.mastermind.persistence.player.model.Player;

import java.util.Optional;

public interface PlayerLastSelectedRepository {
    long getLastSelectedPlayerId();

    Optional<Player> getLastSelectedPlayer();

    boolean saveOrUpdate(long id);
}
