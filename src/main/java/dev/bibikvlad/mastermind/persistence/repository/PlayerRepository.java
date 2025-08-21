package dev.bibikvlad.mastermind.persistence.repository;

import dev.bibikvlad.mastermind.model.player.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    List<Player>  findAll();

    Optional<Player> findById(int id);

    Optional<Player> findByName(String name);

    void save(Player player);

    void delete(Player player);

    void deleteById(int id);

    void deleteByName(String name);

    void update(Player oldPlayer, Player newPlayer);
}
