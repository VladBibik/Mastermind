package dev.bibikvlad.mastermind.menu.main;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.AnsiSafeFormatter;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.MainMenuMessages;
import dev.bibikvlad.mastermind.menu.core.ExitMenu;
import dev.bibikvlad.mastermind.menu.main.game.GameLaunchMenu;
import dev.bibikvlad.mastermind.menu.main.leaderboards.LeaderboardMenu;
import dev.bibikvlad.mastermind.menu.main.profile.ProfileMenu;
import dev.bibikvlad.mastermind.menu.main.settings.SettingsMenu;
import dev.bibikvlad.mastermind.menu.main.stats.StatsMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.GamesService;
import dev.bibikvlad.mastermind.services.LeaderboardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MainMenuTest {
    @Mock
    private AppContext appContext;

    @Mock
    private Player currentPlayer;

    @Mock
    private LocalizationContext localizationContext;

    @Mock
    private ServiceContainer serviceContainer;

    @Mock
    private InteractionMessages interactionMessages;

    @Mock
    private MainMenuMessages mainMenuMessages;

    @Mock
    private Printer printer;

    @Mock
    private Parser parser;

    private MainMenu mainMenu;

    @BeforeEach
    void setUp() {
        when(appContext.currentPlayer()).thenReturn(currentPlayer);
        when(currentPlayer.getPlayerName()).thenReturn("TestPlayer");

        when(appContext.printer()).thenReturn(printer);
        when(appContext.parser()).thenReturn(parser);

        when(appContext.localizationContext()).thenReturn(localizationContext);
        when(localizationContext.getMessages(MessageType.INTERACTION)).thenReturn(interactionMessages);

        when(localizationContext.getMessages(MessageType.MAIN_MENU)).thenReturn(mainMenuMessages);
        when(mainMenuMessages.getWelcome(anyString())).thenReturn("Welcome Dummy String");
        when(mainMenuMessages.getMenuOptions()).thenReturn("Menu Options");

        mainMenu = new MainMenu(appContext);
    }

    @Test
    @DisplayName("Returns MainMenu on invalid input")
    void returnsMainMenuOnInvalidInput() {
        String invalidMessage = "Invalid Input";

        when(interactionMessages.getInvalidInput()).thenReturn(invalidMessage);
        when(parser.parse()).thenReturn("invalid input");

        assertSame(mainMenu, mainMenu.run());

        verify(interactionMessages, times(1)).getInvalidInput();
        verify(printer).printMessage(invalidMessage);
    }

    @Test
    @DisplayName("Passes formatted player name to welcome message")
    void passesFormattedPlayerNameToWelcomeMessage() {
        String currentPlayerName = currentPlayer.getPlayerName();

        when(parser.parse()).thenReturn("close");

        mainMenu.run();

        verify(mainMenuMessages, times(1))
                .getWelcome(AnsiSafeFormatter.isolate(currentPlayerName));
    }

    @Test
    @DisplayName("Doesn't redisplay menu after invalid input")
    void doesNotRedisplayMenuAfterInvalidInput() {
        when(parser.parse()).thenReturn("invalid input", "another invalid input");

        mainMenu.run();
        mainMenu.run();

        verify(mainMenuMessages).getWelcome(anyString());
        verify(mainMenuMessages).getMenuOptions();
    }

    @Test
    @DisplayName("Displays menu only once")
    void displaysMenuOnlyOnce() {
        when(appContext.parser().parse()).thenReturn("close");

        mainMenu.run();

        verify(printer, times(2)).printMessage(anyString());

        clearInvocations(printer);

        mainMenu.run();

        verify(printer, never()).printMessage(anyString());
    }

    @Test
    @DisplayName("Returns game launch menu")
    void returnsGameLaunchMenu() {
        when(appContext.parser().parse()).thenReturn("1");

        when(appContext.services()).thenReturn(serviceContainer);
        when(appContext.services().getGamesService()).thenReturn(mock(GamesService.class));

        assertInstanceOf(GameLaunchMenu.class, mainMenu.run());
    }

    @Test
    @DisplayName("Returns leaderboard menu")
    void returnsLeaderboardMenu() {
        when(appContext.parser().parse()).thenReturn("2");

        when(appContext.services()).thenReturn(serviceContainer);
        when(appContext.services().getLeaderboardService()).thenReturn(mock(LeaderboardService.class));

        assertInstanceOf(LeaderboardMenu.class, mainMenu.run());
    }

    @Test
    @DisplayName("Returns stats menu")
    void returnsStatsMenu() {
        when(appContext.parser().parse()).thenReturn("3");

        when(appContext.services()).thenReturn(serviceContainer);

        assertInstanceOf(StatsMenu.class, mainMenu.run());
    }

    @Test
    @DisplayName("Returns profile menu")
    void returnsProfileMenu() {
        when(appContext.parser().parse()).thenReturn("4");

        when(appContext.services()).thenReturn(serviceContainer);

        assertInstanceOf(ProfileMenu.class, mainMenu.run());
    }

    @Test
    @DisplayName("Returns settings menu")
    void returnsSettingsMenu() {
        when(appContext.parser().parse()).thenReturn("5");

        when(appContext.services()).thenReturn(serviceContainer);

        assertInstanceOf(SettingsMenu.class, mainMenu.run());
    }

    @Test
    @DisplayName("Returns Exit menu when '0' is selected")
    void returnsExitMenuWhenZeroIsSelected() {
        when(appContext.parser().parse()).thenReturn("close");

        assertInstanceOf(ExitMenu.class, mainMenu.run());
    }
}
