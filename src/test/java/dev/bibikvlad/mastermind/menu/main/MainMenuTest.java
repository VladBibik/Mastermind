package dev.bibikvlad.mastermind.menu.main;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.model.player.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainMenuTest {
//    @Test
//    void stubCurrentPlayer() {
//        AppContext appContext = mock(AppContext.class);
//        Player player = mock(Player.class);
//
//        when(appContext.currentPlayer()).thenReturn(player);
//
//        assertSame(player, appContext.currentPlayer());
//    }
    @Mock
    AppContext appContext;

    @Test
    void createAppContextMock() {
        assertNotNull(appContext);
    }
}
