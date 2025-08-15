package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.player.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO {
    private final Connection connection;

    public PlayerDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Player> getPlayers() throws SQLException {
        List<Player> players = new ArrayList<>();
        String fetchAllPlayersQuery = """
                SELECT *
                FROM players p
                LEFT JOIN player_configurations conf
                ON p.id = conf.player_id
                """;

        PreparedStatement preparedStatement = connection.prepareStatement(fetchAllPlayersQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Player player = new Player(
                    resultSet.getString("player_name"),
                    LocaleType.fromLanguageString(resultSet.getString("language")));

            players.add(player);
        }

        return players;
    }
}
