package dev.bibikvlad.mastermind.persistence.repository;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.model.player.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    List<Player> findAll() throws PersistenceException;

    Optional<Player> findById(long id) throws PersistenceException;

    Optional<Player> findByName(String name) throws PersistenceException;

    boolean save(Player player) throws PersistenceException;

    boolean update(Player player) throws PersistenceException;

    void delete(Player player) throws PersistenceException;

    void deleteById(long id) throws PersistenceException;

    void deleteByName(String name) throws PersistenceException;

    boolean existsById(long id) throws PersistenceException;

    boolean existsByName(String name) throws PersistenceException;
}
