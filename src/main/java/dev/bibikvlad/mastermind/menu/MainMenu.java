package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.app.bootstrap.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.MainMenuInputInterpreter;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.error.ErrorMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.MainMenuMessages;
import dev.bibikvlad.mastermind.menu.TEMP_support.GameLaunchFlowHandler;
import dev.bibikvlad.mastermind.menu.games.LeaderboardMenu;
import dev.bibikvlad.mastermind.menu.player.ProfileMenu;
import dev.bibikvlad.mastermind.menu.settings.SettingsMenu;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.services.PlayerStatisticsService;
import dev.bibikvlad.utils.formatters.TimeToStringFormatter;

import java.util.Optional;

public class MainMenu extends Menu {
    private final Player currentPlayer;
    private final Printer printer;
    private final ErrorMessages errorMessages;
    private final MainMenuMessages mainMenuMessages;

    private boolean isFirstTimeMenuLoop = true;

    public MainMenu(AppContext appContext) {
        super(appContext);

        this.currentPlayer = appContext.currentPlayer();
        this.printer = appContext.printer();
        this.errorMessages = appContext.localizationContext().getMessages(MessageType.ERROR);
        this.mainMenuMessages = appContext.localizationContext().getMessages(MessageType.MAIN_MENU);
    }

    @Override
    public Menu run() {
        if (isFirstTimeMenuLoop) {
            displayMenu();
            isFirstTimeMenuLoop = false;
        }

        Optional<Integer> selection = MainMenuInputInterpreter.readSelection(appContext.parser());

        return selection
                .map(this::menuOptionSwitcher)
                .orElse(new ExitMenu(appContext));
    }

    private void displayMenu() {
        printer.printMessage(mainMenuMessages.getWelcomeMessage(currentPlayer.getPlayerName()));
        printer.printMessage(mainMenuMessages.getMenuOptionsMessage());
    }

    private Menu menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> {
                launchGame();

                return this;
            }
            case 2 -> {
                return leaderboards();
            }
            case 3 -> {
                stats();

                return this;
            }
            case 4 -> {
                return profileMenu();
            }
            case 5 -> {
                return settings();
            }
            default -> {
                printer.printMessage(errorMessages.getInvalidInputMessage());

                return this;
            }
        }
    }

    private void launchGame() {
        GameLaunchFlowHandler gameLaunchHandler = new GameLaunchFlowHandler(appContext);
        gameLaunchHandler.launchGame();

        isFirstTimeMenuLoop = true;
    }

    private Menu profileMenu() {
        return new ProfileMenu(appContext);
    }

    //TODO: Move to separate class
    //TODO: Think if 'press any key to continue' needed, because currently menu options are printed right after statistics, and it makes reading statistics harder
    private void stats() {
        PlayerStatisticsService playerStatisticsService = appContext.services().getPlayerStatisticsService();

        playerStatisticsService.getPlayerStatistics(currentPlayer.getId()).ifPresent(playerStatistics -> {
            printer.printMessage("\n" + currentPlayer.getPlayerName() + "'s statistics:");
            printer.printMessage("Games played: " + playerStatistics.gameCount());
            printer.printMessage("Number of wins: " + playerStatistics.winCount());
            printer.printMessage("Win percentage: " + playerStatistics.winPercentage());
            printer.printMessage("Total play time: " + TimeToStringFormatter.format(playerStatistics.totalPlayTime()));
            printer.printMessage("Average game duration: " + TimeToStringFormatter.format(
                    playerStatistics.averageGameDuration()));
            printer.printMessage("Fastest win time: " + TimeToStringFormatter.format(
                    playerStatistics.fastestWinTime()));
            printer.printMessage("Minimum turns needed for a win: " + playerStatistics.minTurnsWin() + "\n");
        });
    }

    private Menu leaderboards() {
        return new LeaderboardMenu(appContext);
    }

    private Menu settings() {
        return new SettingsMenu(appContext);
    }
}
