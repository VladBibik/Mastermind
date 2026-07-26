package dev.bibikvlad.mastermind.app.game;

import dev.bibikvlad.mastermind.app.printer.ConsolePrinter;
import dev.bibikvlad.mastermind.game.MastermindConsoleGame;
import dev.bibikvlad.mastermind.game.RandomAnswerGenerator;
import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.game.presentation.GameMessagePrinter;
import dev.bibikvlad.mastermind.input.parser.ConsoleInputParser;
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

    public MastermindGameBootstrap(LocalizationContext localizationContext, LogoColorsBundle logoColorsBundle,
                                   Parser parser) {
        this.localizationContext = localizationContext;
        this.logoColorsBundle = logoColorsBundle;
        this.parser = parser;
    }

    public GameData launch() {
        GameMessages gameMessages = localizationContext.getMessages(MessageType.GAME);
        GameMessagePrinter printer = new GameMessagePrinter(new ConsolePrinter(), gameMessages);

        MastermindConsoleGame game = new MastermindConsoleGame(printer, new LowerCaseParser(parser),
                RandomAnswerGenerator.generate(), logoColorsBundle);

        return TimedGameRunner.launch(game);
    }
}
