package dev.bibikvlad.mastermind.persistence.player.dao;

import dev.bibikvlad.mastermind.model.player.Player;

import java.util.Optional;

public interface PlayerLastSelectedDAO {
    long getLastSelectedPlayerId();

    Optional<Player> getLastSelectedPlayer();

    boolean saveOrUpdate(long id);
}
