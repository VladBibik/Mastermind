package dev.bibikvlad.mastermind.menu.main;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.app.context.AppContext;
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
        when(appContext.printer()).thenReturn(printer);
        when(appContext.parser()).thenReturn(parser);
        when(appContext.localizationContext()).thenReturn(localizationContext);
        when(localizationContext.getMessages(MessageType.INTERACTION)).thenReturn(interactionMessages);
        when(localizationContext.getMessages(MessageType.MAIN_MENU)).thenReturn(mainMenuMessages);
    }

    @Test
    @DisplayName("Does not display the menu again")
    void doesNotDisplayMenuAgain() {
        mainMenu = new MainMenu(appContext);

        stubDisplayMenuMessages();
        when(appContext.parser().parse()).thenReturn("close");

        mainMenu.run();

        verify(printer, times(2)).printMessage(anyString());

        clearInvocations(printer);

        mainMenu.run();

        verify(printer, never()).printMessage(anyString());
    }

    @Test
    @DisplayName("Launches the game")
    void launchesGame() {
        mainMenu = new MainMenu(appContext);

        stubDisplayMenuMessages();
        when(appContext.services()).thenReturn(mock(ServiceContainer.class));
        when(appContext.services().getGamesService()).thenReturn(mock(GamesService.class));
        when(appContext.parser().parse()).thenReturn("1");

        assertInstanceOf(GameLaunchMenu.class, mainMenu.run());
    }

    @Test
    @DisplayName("Launches leaderboard menu")
    void launchesLeaderboardMenu() {
        mainMenu = new MainMenu(appContext);

        stubDisplayMenuMessages();
        when(appContext.services()).thenReturn(mock(ServiceContainer.class));
        when(appContext.services().getLeaderboardService()).thenReturn(mock(LeaderboardService.class));
        when(appContext.parser().parse()).thenReturn("2");

        assertInstanceOf(LeaderboardMenu.class, mainMenu.run());
    }

    @Test
    @DisplayName("Launches stats menu")
    void launchesStatsMenu() {
        mainMenu = new MainMenu(appContext);

        stubDisplayMenuMessages();
        when(appContext.services()).thenReturn(mock(ServiceContainer.class));
        when(appContext.parser().parse()).thenReturn("3");

        assertInstanceOf(StatsMenu.class, mainMenu.run());
    }

    @Test
    @DisplayName("Launches profile menu")
    void launchesProfileMenu() {
        mainMenu = new MainMenu(appContext);

        stubDisplayMenuMessages();
        when(appContext.services()).thenReturn(mock(ServiceContainer.class));
        when(appContext.parser().parse()).thenReturn("4");

        assertInstanceOf(ProfileMenu.class, mainMenu.run());
    }

    @Test
    @DisplayName("Launches settings menu")
    void launchesSettingsMenu() {
        mainMenu = new MainMenu(appContext);

        stubDisplayMenuMessages();
        when(appContext.services()).thenReturn(mock(ServiceContainer.class));
        when(appContext.parser().parse()).thenReturn("5");

        assertInstanceOf(SettingsMenu.class, mainMenu.run());
    }

    @Test
    @DisplayName("0 input launches ExitMenu subsequently leading to the program shut down")
    void zeroValueReturnsExitMenu() {
        mainMenu = new MainMenu(appContext);

        stubDisplayMenuMessages();
        when(appContext.parser().parse()).thenReturn("0");

        assertInstanceOf(ExitMenu.class, mainMenu.run());
    }

    private void stubDisplayMenuMessages() {
        when(mainMenuMessages.getWelcome(anyString())).thenReturn("Welcome Dummy String");
        when(mainMenuMessages.getMenuOptions()).thenReturn("Menu Options");
    }
}
