package dev.bibikvlad.mastermind.menu.main;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.model.player.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class MainMenuTest {
    @Test
    void stubCurrentPlayer() {
        AppContext appContext = mock(AppContext.class);
        Player player = mock(Player.class);

        when(appContext.currentPlayer()).thenReturn(player);

        assertSame(player, appContext.currentPlayer());
    }
}
