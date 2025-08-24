package dev.bibikvlad.mastermind.persistence.repository;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.exceptions.PlayerNotFoundException;
import dev.bibikvlad.mastermind.model.player.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerDAO {
    List<Player> findAll() throws PersistenceException;

    Optional<Player> findById(int id) throws PersistenceException;

    Optional<Player> findByName(String name) throws PersistenceException;

    void save(Player player) throws PersistenceException, PlayerAlreadyExistException;

    void delete(Player player) throws PersistenceException, PlayerNotFoundException;

    void deleteById(int id) throws PersistenceException, PlayerNotFoundException;

    void deleteByName(String name) throws PersistenceException, PlayerNotFoundException;

    void update(Player oldPlayer, Player newPlayer) throws PersistenceException, PlayerNotFoundException;

    boolean existsById(int id) throws PersistenceException;

    boolean existsByName(String name) throws PersistenceException;
}
