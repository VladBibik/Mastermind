package dev.bibikvlad.mastermind.menu.main;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainMenuTest {
    @Mock
    private AppContext appContext;

    @Mock
    private Player currentPlayer;

    @Mock
    private Printer printer;

    @BeforeEach
    void setUp() {
        when(appContext.currentPlayer()).thenReturn(currentPlayer);

        when(appContext.printer()).thenReturn(printer);
    }
}
