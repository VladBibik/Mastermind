package dev.bibikvlad.mastermind.menu.TEMP_support;

import dev.bibikvlad.mastermind.app.bootstrap.AppContext;
import dev.bibikvlad.mastermind.app.game.MastermindGameBootstrap;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.game.data.GameResult;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.services.GamesService;

public class GameLaunchFlowHandler {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final Printer printer;
    private final GamesService gamesService;
    private final Player currentPlayer;
    private final MastermindGameBootstrap mastermindGameLauncher;

    public GameLaunchFlowHandler(AppContext appContext) {
        this.localizationContext = appContext.localizationContext();
        this.parser = appContext.parser();
        this.printer = appContext.printer();
        this.gamesService = appContext.services().getGamesService();
        this.currentPlayer = appContext.currentPlayer();
        this.mastermindGameLauncher = new MastermindGameBootstrap(localizationContext,
                currentPlayer.getPlayerConfig().logoColorsBundle());
    }

    public void launchGame() {
        GameData gameData = mastermindGameLauncher.launch();

        afterGameDataSaving(gameData);
    }

    private void afterGameDataSaving(GameData gameData) {
        gamesService.save(currentPlayer.getId(), gameData);

        nextStepHandler(gameData);
    }

    private void nextStepHandler(GameData gameData) {
        GameResult gameResult = gameData.getGameOutcome().getResult();

        if (gameResult.equals(GameResult.LOSE) || gameResult.equals(GameResult.WIN)) {
            printer.printMessage("\nPrint any key to play again");
            printer.printMessage("Print '0' to return to main menu");

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
