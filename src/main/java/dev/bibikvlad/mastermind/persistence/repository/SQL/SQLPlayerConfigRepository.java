package dev.bibikvlad.mastermind.persistence.repository.SQL;

import dev.bibikvlad.mastermind.model.player.PlayerConfig;
import dev.bibikvlad.mastermind.persistence.dao.PlayerConfigDAO;

public class SQLPlayerConfigRepository {
    private final PlayerConfigDAO playerConfigDAO;

    public SQLPlayerConfigRepository(PlayerConfigDAO playerConfigDAO) {
        this.playerConfigDAO = playerConfigDAO;
    }
}
