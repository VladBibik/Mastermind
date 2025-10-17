package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.player.Player;

import java.util.Optional;

public interface PlayerLastSelectedDAO {
    boolean saveOrUpdate(long id) throws PersistenceException;

    long getLastSelectedPlayerId() throws PersistenceException;

    Optional<Player> getLastSelectedPlayer() throws PersistenceException;
}
