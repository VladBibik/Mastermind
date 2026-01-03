package dev.bibikvlad.mastermind.persistence.game.dao;

import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.model.game.Game;

import java.util.List;

public interface GameDAO {
    List<Game> findAll();

    List<Game> findAllByPlayerId(long playerId);

    boolean save(long playerId, GameData gameData);

    int count();

    int countByPlayerId(long playerId);
}
