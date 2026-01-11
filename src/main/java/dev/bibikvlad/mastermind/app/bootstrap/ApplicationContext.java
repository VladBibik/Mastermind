package dev.bibikvlad.mastermind.app.bootstrap;

import dev.bibikvlad.mastermind.services.GamesService;
import dev.bibikvlad.mastermind.services.LeaderboardService;
import dev.bibikvlad.mastermind.services.PlayerService;
import dev.bibikvlad.mastermind.services.PlayerStatisticsService;

import java.sql.Connection;

public class ApplicationContext {
    private final Connection connection;

    private PlayerService playerService;
    private GamesService gamesService;
    private LeaderboardService leaderboardService;
    private PlayerStatisticsService playerStatisticsService;

    public ApplicationContext(Connection connection) {
        this.connection = connection;
    }
}
