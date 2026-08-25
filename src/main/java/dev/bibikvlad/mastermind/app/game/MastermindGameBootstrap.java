package dev.bibikvlad.mastermind.app.game;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.game.MastermindConsoleGame;
import dev.bibikvlad.mastermind.game.RandomAnswerGenerator;
import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.input.parser.LowerCaseParser;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;

public class MastermindGameBootstrap {
    private final LocalizationContext localizationContext;
    private final LogoColorsBundle logoColorsBundle;
    private final Parser parser;
    private final Printer printer;

    public MastermindGameBootstrap(AppContext appContext) {
        this.localizationContext = appContext.localizationContext();
        this.logoColorsBundle = appContext.currentPlayer().getPlayerConfig().logoColorsBundle();
        this.parser = appContext.parser();
        this.printer = appContext.printer();
    }

    public GameData launch() {
        GameMessages gameMessages = localizationContext.getMessages(MessageType.GAME);
        Parser lowerCaseParser = new LowerCaseParser(parser);

        MastermindConsoleGame game = new MastermindConsoleGame(printer, lowerCaseParser, gameMessages,
                RandomAnswerGenerator.generate(), logoColorsBundle);

        return TimedGameRunner.launch(game);
    }
}
