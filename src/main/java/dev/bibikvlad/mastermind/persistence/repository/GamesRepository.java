package dev.bibikvlad.mastermind.persistence.repository;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.model.game.Game;

import java.util.List;

public interface GamesRepository {
    List<Game> findAll() throws PersistenceException;

    List<Game> findAllByPlayerId(long playerId) throws PersistenceException;

    boolean save(long playerId, GameData gameData) throws PersistenceException;

    int count() throws PersistenceException;
}
