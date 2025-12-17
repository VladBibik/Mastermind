package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.model.game.Game;

import java.util.List;

public interface GamesDAO {
    List<Game> findAll() throws PersistenceException;

    List<Game> findAllById() throws PersistenceException;

    boolean save(long playerId, GameData gameData) throws PersistenceException;

    int count() throws PersistenceException;
}
