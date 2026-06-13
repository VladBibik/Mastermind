package dev.bibikvlad.mastermind.persistence.player.repository;

import dev.bibikvlad.mastermind.model.player.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    List<Player> findAll();

    Optional<Player> findById(long id);

    Optional<Player> findByName(String name);

    Player save(Player player);

    boolean update(Player player);

    boolean updatePlayerName(long id, String name);

    void delete(Player player);

    void deleteById(long id);

    void deleteByName(String name);

    boolean existsById(long id);

    boolean existsByName(String name);

    int count();
}
