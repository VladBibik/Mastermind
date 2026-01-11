package dev.bibikvlad.mastermind.app.bootstrap;

import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
import dev.bibikvlad.mastermind.persistence.game.dao.GameDAO;
import dev.bibikvlad.mastermind.persistence.game.jdbc.GameJdbcDAO;
import dev.bibikvlad.mastermind.persistence.game.repository.GamesRepository;
import dev.bibikvlad.mastermind.persistence.game.repository.sql.GamesSQLRepository;
import dev.bibikvlad.mastermind.persistence.player.dao.PlayerConfigDAO;
import dev.bibikvlad.mastermind.persistence.player.dao.PlayerDAO;
import dev.bibikvlad.mastermind.persistence.player.dao.PlayerLastSelectedDAO;
import dev.bibikvlad.mastermind.persistence.player.dao.jdbc.PlayerConfigJdbcDAO;
import dev.bibikvlad.mastermind.persistence.player.dao.jdbc.PlayerJdbcDAO;
import dev.bibikvlad.mastermind.persistence.player.dao.jdbc.PlayerLastSelectedJdbcDAO;
import dev.bibikvlad.mastermind.persistence.player.repository.PlayerConfigRepository;
import dev.bibikvlad.mastermind.persistence.player.repository.PlayerLastSelectedRepository;
import dev.bibikvlad.mastermind.persistence.player.repository.PlayerRepository;
import dev.bibikvlad.mastermind.persistence.player.repository.sql.PlayerConfigSQLRepository;
import dev.bibikvlad.mastermind.persistence.player.repository.sql.PlayerLastSelectedSQLRepository;
import dev.bibikvlad.mastermind.persistence.player.repository.sql.PlayerSQLRepository;
import dev.bibikvlad.mastermind.services.GamesService;
import dev.bibikvlad.mastermind.services.LeaderboardService;
import dev.bibikvlad.mastermind.services.PlayerService;
import dev.bibikvlad.mastermind.services.PlayerStatisticsService;

import java.sql.Connection;

public class ApplicationContext {
    private final Connection connection;
    private final TransactionManager transactionManager;

    private PlayerService playerService;
    private GamesService gamesService;
    private LeaderboardService leaderboardService;
    private PlayerStatisticsService playerStatisticsService;

    public ApplicationContext(Connection connection, TransactionManager transactionManager) {
        this.connection = connection;
        this.transactionManager = transactionManager;
    }

    public PlayerService getPlayerService() {
        if (playerService == null) {
            PlayerDAO playerDAO = new PlayerJdbcDAO(connection);
            PlayerConfigDAO playerConfigDAO = new PlayerConfigJdbcDAO(connection);
            PlayerLastSelectedDAO playerLastSelectedDAO = new PlayerLastSelectedJdbcDAO(connection);

            PlayerRepository playerRepository = new PlayerSQLRepository(playerDAO, transactionManager);
            PlayerConfigRepository playerConfigRepository =
                    new PlayerConfigSQLRepository(playerConfigDAO, transactionManager);
            PlayerLastSelectedRepository playerLastSelectedRepository =
                    new PlayerLastSelectedSQLRepository(playerLastSelectedDAO, transactionManager);

            playerService = new PlayerService(playerRepository, playerConfigRepository,
                    playerLastSelectedRepository);
        }

        return playerService;
    }

    public GamesService getGamesService() {
        if (gamesService == null) {
            //TODO: Rename it to GamesDAO!
            GameDAO gameDAO = new GameJdbcDAO(connection);
            GamesRepository gamesRepository = new GamesSQLRepository(gameDAO, transactionManager);
            gamesService = new GamesService(gamesRepository);
        }

        return gamesService;
    }
}
