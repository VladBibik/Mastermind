package dev.bibikvlad.mastermind.menu.TEMP_support;

import dev.bibikvlad.mastermind.app.bootstrap.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
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

    public GameLaunchFlowHandler(AppContext appContext) {
        this.localizationContext = appContext.localizationContext();
        this.parser = appContext.parser();
        this.printer = appContext.printer();
        this.gamesService = appContext.services().getGamesService();
        this.currentPlayer = appContext.currentPlayer();
    }
}
