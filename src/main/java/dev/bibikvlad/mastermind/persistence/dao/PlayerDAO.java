package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.model.player.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerDAO {
    List<Player> findAll();

    Optional<Player> findById(long id);

    Optional<Player> findByName(String name);

    boolean save(Player player);

    boolean update(Player player);

    boolean updatePlayerName(long id, String name);

    boolean delete(Player player);

    boolean deleteById(long id);

    boolean deleteByName(String name);

    boolean existsById(long id);

    boolean existsByName(String name);

    int count();
}
