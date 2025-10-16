package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;

public interface PlayerLastSelectedDAO {
    boolean saveOrUpdate(long id) throws PersistenceException;
    long getLastSelected() throws PersistenceException;
}
