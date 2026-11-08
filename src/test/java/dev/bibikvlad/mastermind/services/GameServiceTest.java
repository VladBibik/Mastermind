package dev.bibikvlad.mastermind.services;

import dev.bibikvlad.mastermind.persistence.game.repository.GamesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GamesServiceTest {
    @Mock
    private GamesRepository gamesRepository;

    private GamesService gamesService;

    @BeforeEach
    void setUp() {
        gamesService = new GamesService(gamesRepository);
    }
}
