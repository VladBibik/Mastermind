package dev.bibikvlad.mastermind.persistence.dao;

import dev.bibikvlad.mastermind.database.DatabaseContext;
import dev.bibikvlad.mastermind.exceptions.PersistenceException;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.exceptions.PlayerNotFoundException;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.model.player.PlayerConfig;
import dev.bibikvlad.mastermind.persistence.repository.PlayerRepository;
import dev.bibikvlad.utils.formatters.SQLiteTimestampFormatter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcPlayerRepository implements PlayerRepository {
    private final Connection connection;

    public JdbcPlayerRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Player> findAll() throws PersistenceException {
        List<Player> players = new ArrayList<>();
        String fetchAllPlayersQuery = """
                SELECT *
                FROM players p
                LEFT JOIN player_configurations conf
                ON p.id = conf.player_id
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(fetchAllPlayersQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                PlayerConfig playerConfig = new PlayerConfig(
                        resultSet.getInt("id"),
                        LocaleType.fromLanguageString(resultSet.getString("language")));
                Player player = new Player(
                        resultSet.getString("player_name"),
                        SQLiteTimestampFormatter.parse(resultSet.getString("creation_date")),
                        playerConfig);

                players.add(player);
            }

            return players;
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch all players", exception);
        }
    }

    @Override
    public Optional<Player> findById(int playerId) throws PersistenceException {
        String fetchPlayerQuery = """
                        SELECT *
                        FROM players p
                        LEFT JOIN player_configurations conf
                        ON p.id = conf.player_id
                        WHERE p.id = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(fetchPlayerQuery)) {
            preparedStatement.setInt(1, playerId);
            return getPlayer(preparedStatement);
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch player by ID: " + playerId, exception);
        }
    }

    @Override
    public Optional<Player> findByName(String playerName) throws PersistenceException {
        String fetchPlayerQuery = """
                        SELECT *
                        FROM players p
                        LEFT JOIN player_configurations conf
                        ON p.id = conf.player_id
                        WHERE p.player_name = ?
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(fetchPlayerQuery)) {
            preparedStatement.setString(1, playerName);
            return getPlayer(preparedStatement);
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to fetch player by Player Name: " + playerName, exception);
        }
    }

    private Optional<Player> getPlayer(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            PlayerConfig playerConfig = new PlayerConfig(
                    resultSet.getInt("id"),
                    LocaleType.fromLanguageString(resultSet.getString("language")));
            Player player = new Player(
                    resultSet.getString("player_name"),
                    SQLiteTimestampFormatter.parse(resultSet.getString("creation_date")),
                    playerConfig);

            return Optional.of(player);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void save(Player player) throws PersistenceException, PlayerAlreadyExistException {
        if (existsByName(player.getPlayerName())) {
            throw new PlayerAlreadyExistException("Player with the name: "
                    + player.getPlayerName() + " already exist!");
        }

        String addPlayerQuery = """
                INSERT INTO players (player_name) VALUES (?)
                """;
        String addPlayerConfigQuery = """
                INSERT INTO player_configurations (player_id, language) VALUES (?, ?)
                """;

        try (PreparedStatement playerPreparedStatement =
                     connection.prepareStatement(addPlayerQuery, Statement.RETURN_GENERATED_KEYS)) {
            PreparedStatement configPreparedStatement = connection.prepareStatement(addPlayerConfigQuery);

            playerPreparedStatement.setString(1, player.getPlayerName());
            playerPreparedStatement.executeUpdate();

            try (ResultSet generatedKeys = playerPreparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int playerId = generatedKeys.getInt(1);

                    configPreparedStatement.setInt(1, playerId);
                    configPreparedStatement.setString(2,
                            player.getPlayerConfig().getLocale().getLanguageName());
                    configPreparedStatement.executeUpdate();
                } else {
                    throw new PersistenceException("Creating player failed. No ID obtained.");
                }
            }
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to save a Player", exception);
        }
    }

    @Override
    public void delete(Player player) throws PersistenceException, PlayerNotFoundException {
        if (!existsByName(player.getPlayerName()))
            throw new PlayerNotFoundException("Player with the name: '" + player.getPlayerName() + "' does not exist");

        String deletePlayerQuery = """
                            DELETE FROM players
                            WHERE player_name = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(deletePlayerQuery)) {
            preparedStatement.setString(1, player.getPlayerName());
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to delete a Player", exception);
        }
    }

    @Override
    public void deleteById(int playerId) throws PersistenceException, PlayerNotFoundException {
        if (!existsById(playerId))
            throw new PlayerNotFoundException("Player with id: '" + playerId + "' does not exist");

        String deletePlayerQuery = """
                        DELETE FROM players
                        WHERE id = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(deletePlayerQuery)) {
            preparedStatement.setInt(1, playerId);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to delete player by ID: " + playerId, exception);
        }
    }

    @Override
    public void deleteByName(String playerName) throws PersistenceException, PlayerNotFoundException {
        if (!existsByName(playerName))
            throw new PlayerNotFoundException("Player with the name: '" + playerName + "' does not exist");

        String deletePlayerQuery = """
                        DELETE FROM players
                        WHERE player_name = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(deletePlayerQuery)) {
            preparedStatement.setString(1, playerName);
            preparedStatement.executeUpdate();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to delete player by Name: " + playerName, exception);
        }
    }

    @Override
    public void update(Player oldPlayer, Player newPlayer) throws PersistenceException, PlayerNotFoundException {
        if (!existsByName(oldPlayer.getPlayerName())) {
            throw new PlayerNotFoundException(
                    "Player with the name: '" + oldPlayer.getPlayerName() + "' does not exist");
        }

        String updatePlayerQuery = """
                        UPDATE players
                        SET player_name = ?
                        WHERE player_name = ?;
                """;
        String updateConfigQuery = """
                        UPDATE player_configurations
                        SET language = ?
                        WHERE player_id = ?;
                """;

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(updatePlayerQuery, Statement.RETURN_GENERATED_KEYS)) {
            PreparedStatement configPreparedStatement = connection.prepareStatement(updateConfigQuery);

            preparedStatement.setString(1, newPlayer.getPlayerName());
            preparedStatement.setString(2, oldPlayer.getPlayerName());

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int playerId = generatedKeys.getInt("id");

                    configPreparedStatement.setString(1,
                            newPlayer.getPlayerConfig().getLocale().getLanguageName());
                    configPreparedStatement.setInt(2, playerId);
                    configPreparedStatement.executeUpdate();
                }
            }
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to update a Player: " + oldPlayer, exception);
        }
    }

    @Override
    public boolean existsById(int playerId) throws PersistenceException {
        String playerQuery = """
                            SELECT player_name FROM players
                            WHERE id = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(playerQuery)) {
            preparedStatement.setInt(1, playerId);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to check if player with id: " + playerId + " exists", exception);
        }
    }

    @Override
    public boolean existsByName(String playerName) throws PersistenceException {
        String playerQuery = """
                            SELECT player_name FROM players
                            WHERE player_name = ?;
                """;

        try (PreparedStatement preparedStatement = connection.prepareStatement(playerQuery)) {
            preparedStatement.setString(1, playerName);
            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();
        } catch (SQLException exception) {
            throw new PersistenceException("Failed to check if player with name: " + playerName + " exists", exception);
        }
    }
}

class Test {
    public static void main(String[] args) throws PersistenceException {
        JdbcPlayerRepository jdbcPlayerRepository = new JdbcPlayerRepository(DatabaseContext.getConnection());

        //TODO: Fix issue with exception on same player registration!
        PlayerConfig playerConfig = new PlayerConfig(LocaleType.ENGLISH);
        Player player = new Player("Maele", playerConfig);

        jdbcPlayerRepository.save(player);

        System.out.println(jdbcPlayerRepository.findAll());
    }
}