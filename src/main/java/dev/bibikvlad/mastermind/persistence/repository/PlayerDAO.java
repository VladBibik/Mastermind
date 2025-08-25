package dev.bibikvlad.mastermind.persistence.repository;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.player.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerDAO {
    List<Player> findAll() throws PersistenceException;

    Optional<Player> findById(int id) throws PersistenceException;

    Optional<Player> findByName(String name) throws PersistenceException;

    void save(Player player) throws PersistenceException;

    void delete(Player player) throws PersistenceException;

    void deleteById(int id) throws PersistenceException;

    void deleteByName(String name) throws PersistenceException;

    void update(Player player) throws PersistenceException;

    boolean existsById(int id) throws PersistenceException;

    boolean existsByName(String name) throws PersistenceException;
}
