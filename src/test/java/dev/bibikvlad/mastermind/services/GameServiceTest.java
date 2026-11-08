package dev.bibikvlad.mastermind.services;

import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.game.data.GameOutcome;
import dev.bibikvlad.mastermind.game.data.GameResult;
import dev.bibikvlad.mastermind.model.game.NewGame;
import dev.bibikvlad.mastermind.persistence.game.repository.GamesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyByte;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GamesServiceTest {
    @Mock
    private GamesRepository gamesRepository;

    private GamesService gamesService;

    @BeforeEach
    void setUp() {
        gamesService = new GamesService(gamesRepository);
    }

    @Test
    @DisplayName("Saves game data provided by NewGame object")
    void savesDataProvidedByParameter() {
        NewGame newGame = new NewGame(
                1L,
                new GameData(
                        new GameOutcome(10, GameResult.WIN),
                        1337)
        );

        when(gamesRepository.save(newGame.playerId(), newGame.gameData())).thenReturn(true);

        assertTrue(gamesService.save(newGame));

        verify(gamesRepository).save(newGame.playerId(), newGame.gameData());
    }

    @Test
    @DisplayName("Saves game data provided by GameData and playerId params")
    void savesDataProvidedByTwoParameters() {
        long playerId = 7L;
        GameData gameData = new GameData(new GameOutcome(10, GameResult.WIN), 1337);

        when(gamesRepository.save(playerId, gameData)).thenReturn(true);

        assertTrue(gamesService.save(playerId, gameData));

        verify(gamesRepository).save(playerId, gameData);
    }

    @Test
    @DisplayName("Returns max number of games played by a player")
    void returnsMaxGamesPlayedByPlayer() {
        int maxGames = 777;
        when(gamesRepository.getMaxGamesPlayedByPlayer()).thenReturn(maxGames);

        assertEquals(maxGames, gamesService.getMaxGamesPlayedByPlayer());

        verify(gamesRepository).getMaxGamesPlayedByPlayer();
    }
}
