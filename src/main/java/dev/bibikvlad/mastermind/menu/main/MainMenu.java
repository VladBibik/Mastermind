package dev.bibikvlad.mastermind.menu.main;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.AnsiSafeFormatter;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.MainMenuInputInterpreter;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.MainMenuMessages;
import dev.bibikvlad.mastermind.menu.core.ExitMenu;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.game.GameLaunchMenu;
import dev.bibikvlad.mastermind.menu.main.leaderboards.LeaderboardMenu;
import dev.bibikvlad.mastermind.menu.main.profile.ProfileMenu;
import dev.bibikvlad.mastermind.menu.main.settings.SettingsMenu;
import dev.bibikvlad.mastermind.menu.main.stats.StatsMenu;
import dev.bibikvlad.mastermind.model.player.Player;

import java.util.Optional;

public class MainMenu extends Menu {
    private final Player currentPlayer;
    private final Printer printer;
    private final InteractionMessages interactionMessages;
    private final MainMenuMessages mainMenuMessages;

    private boolean showMenuOnNextLoop = true;

    public MainMenu(AppContext appContext) {
        super(appContext);

        this.currentPlayer = appContext.currentPlayer();
        this.printer = appContext.printer();
        this.interactionMessages = appContext.localizationContext().getMessages(MessageType.INTERACTION);
        this.mainMenuMessages = appContext.localizationContext().getMessages(MessageType.MAIN_MENU);
    }

    @Override
    public Menu run() {
        if (showMenuOnNextLoop) {
            displayMenu();
            showMenuOnNextLoop = false;
        }

        Optional<Integer> selection = MainMenuInputInterpreter.readSelection(appContext.parser());

        return selection
                .map(this::menuOptionSwitcher)
                .orElse(new ExitMenu(appContext));
    }

    private void displayMenu() {
        String playerName = AnsiSafeFormatter.isolate(currentPlayer.getPlayerName());

        printer.printMessage(mainMenuMessages.getWelcome(playerName));
        printer.printMessage(mainMenuMessages.getMenuOptions());
    }

    private Menu menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> {
                return launchGame();
            }
            case 2 -> {
                return leaderboards();
            }
            case 3 -> {
                return stats();
            }
            case 4 -> {
                return profileMenu();
            }
            case 5 -> {
                return settings();
            }
            default -> {
                printer.printMessage(interactionMessages.getInvalidInput());

                return this;
            }
        }
    }

    private Menu launchGame() {
        return new GameLaunchMenu(appContext);
    }

    private Menu profileMenu() {
        return new ProfileMenu(appContext);
    }

    private Menu stats() {
        return new StatsMenu(appContext);
    }

    private Menu leaderboards() {
        return new LeaderboardMenu(appContext);
    }

    private Menu settings() {
        return new SettingsMenu(appContext);
    }
}
