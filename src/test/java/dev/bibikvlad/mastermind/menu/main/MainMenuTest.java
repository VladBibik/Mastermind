package dev.bibikvlad.mastermind.menu.main;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.MainMenuMessages;
import dev.bibikvlad.mastermind.model.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MainMenuTest {
    @Mock
    private AppContext appContext;

    @Mock
    private Player currentPlayer;

    @Mock
    private LocalizationContext localizationContext;

    @Mock
    InteractionMessages interactionMessages;

    @Mock
    MainMenuMessages mainMenuMessages;

    @Mock
    private Printer printer;

    @BeforeEach
    void setUp() {
        when(appContext.currentPlayer()).thenReturn(currentPlayer);
        when(appContext.printer()).thenReturn(printer);
        when(appContext.localizationContext()).thenReturn(localizationContext);
        when(appContext.localizationContext().getMessages(MessageType.INTERACTION)).thenReturn(interactionMessages);
        when(appContext.localizationContext().getMessages(MessageType.MAIN_MENU)).thenReturn(mainMenuMessages);
    }

    @Test
    void isTrulyNull() {

        assertSame(currentPlayer, appContext.currentPlayer());
    }
}
