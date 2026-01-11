package dev.bibikvlad.mastermind.app.bootstrap;

import dev.bibikvlad.mastermind.persistence.database.TransactionManager;
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
}
