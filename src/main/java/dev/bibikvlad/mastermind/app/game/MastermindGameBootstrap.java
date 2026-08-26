package dev.bibikvlad.mastermind.app.game;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.game.mode.CompatibilityModeMessages;
import dev.bibikvlad.mastermind.app.game.mode.DefaultModeMessages;
import dev.bibikvlad.mastermind.app.game.mode.GameModeDependentMessages;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.game.MastermindConsoleGame;
import dev.bibikvlad.mastermind.game.RandomAnswerGenerator;
import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.input.parser.LowerCaseParser;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.LocalizationType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class MastermindGameBootstrap {
    private final LocalizationContext localizationContext;
    private final Player currentPlayer;
    private final Parser parser;
    private final Printer printer;
    private final PlayerService playerService;

    public MastermindGameBootstrap(AppContext appContext) {
        this.localizationContext = appContext.localizationContext();
        this.currentPlayer = appContext.currentPlayer();
        this.parser = appContext.parser();
        this.printer = appContext.printer();
        this.playerService = appContext.services().getPlayerService();
    }

    public GameData launch() {
        GameMessages gameMessages = localizationContext.getMessages(MessageType.GAME);
        Parser lowerCaseParser = new LowerCaseParser(parser);

        MastermindConsoleGame game = new MastermindConsoleGame(printer, lowerCaseParser, gameMessages,
                RandomAnswerGenerator.generate(), getGameModeDependentMessages());

        return TimedGameRunner.launch(game);
    }

    private GameModeDependentMessages getGameModeDependentMessages() {
        LocalizationType localizationType = currentPlayer.getPlayerConfig().localizationType();

        if (playerService.isInCompatibilityMode(localizationType)) {
            return new CompatibilityModeMessages();
        }

        LogoColorsBundle logoColorsBundle = currentPlayer.getPlayerConfig().logoColorsBundle();

        return new DefaultModeMessages(logoColorsBundle);
    }
}
