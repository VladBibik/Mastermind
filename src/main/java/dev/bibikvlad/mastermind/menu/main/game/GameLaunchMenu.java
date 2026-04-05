package dev.bibikvlad.mastermind.menu.main.game;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.game.MastermindGameBootstrap;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.game.data.GameResult;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.menu.main.game.GameMenuMessages;
import dev.bibikvlad.mastermind.menu.main.MainMenu;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.GamesService;

public class GameLaunchMenu extends Menu {
    private final GamesService gamesService;
    private final Player currentPlayer;
    private final MastermindGameBootstrap mastermindGameLauncher;
    private final GameMenuMessages gameMenuMessages;
    private final Parser parser;
    private final Printer printer;

    public GameLaunchMenu(AppContext appContext) {
        super(appContext);

        this.gamesService = appContext.services().getGamesService();
        this.currentPlayer = appContext.currentPlayer();

        LogoColorsBundle logoBundle = currentPlayer.getPlayerConfig().logoColorsBundle();
        LocalizationContext localizationContext = appContext.localizationContext();

        this.gameMenuMessages = localizationContext.getMessages(MessageType.GAME_MENU);
        this.mastermindGameLauncher = new MastermindGameBootstrap(localizationContext, logoBundle);
        this.parser = appContext.parser();
        this.printer = appContext.printer();
    }

    @Override
    public Menu run() {
        launchGame();

        return new MainMenu(appContext);
    }

    public void launchGame() {
        GameData gameData = mastermindGameLauncher.launch();

        afterGameDataSaving(gameData);
    }

    private void afterGameDataSaving(GameData gameData) {
        gamesService.save(currentPlayer.getId(), gameData);

        playAgain(gameData);
    }

    private void playAgain(GameData gameData) {
        GameResult gameResult = gameData.getGameOutcome().getResult();

        if (gameResult.equals(GameResult.LOSE) || gameResult.equals(GameResult.WIN)) {
            printer.printMessage(gameMenuMessages.playAgain());

            try {
                String input = parser.parseUserInput();
                int selectedNumber = Integer.parseInt(input);

                if (selectedNumber != 0) {
                    launchGame();
                }
            } catch (NumberFormatException exception) {
                launchGame();
            }
        }
    }
}
