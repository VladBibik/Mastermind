package dev.bibikvlad.mastermind.persistence.player.repository;

import dev.bibikvlad.mastermind.model.player.Player;

import java.util.Optional;

public interface PlayerLastSelectedRepository {
    long getLastSelectedPlayerId();

    Optional<Player> getLastSelectedPlayer();

    boolean saveOrUpdate(long id);
}
