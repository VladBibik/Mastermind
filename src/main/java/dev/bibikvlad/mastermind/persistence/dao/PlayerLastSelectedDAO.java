package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;

public interface PlayerLastSelectedDAO {
    boolean saveOrUpdate(int id) throws PersistenceException;
    int getLastSelected() throws PersistenceException;
}
