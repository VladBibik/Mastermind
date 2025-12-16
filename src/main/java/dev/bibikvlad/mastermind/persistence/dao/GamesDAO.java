package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.game.data.GameData;

import java.util.List;

public interface GamesDAO {
    List<GameData> findAll() throws PersistenceException;

    List<GameData> findAllById() throws PersistenceException;

    boolean save(GameData gameData) throws PersistenceException;

    int count() throws PersistenceException;
}
