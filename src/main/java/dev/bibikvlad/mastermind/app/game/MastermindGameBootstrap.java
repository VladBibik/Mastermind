package dev.bibikvlad.mastermind.app.game;

import dev.bibikvlad.mastermind.game.MastermindConsoleGame;
import dev.bibikvlad.mastermind.game.RandomAnswerGenerator;
import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.input.parser.ConsoleInputToLowerCaseParser;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.game.printer.ConsoleMessagePrinter;
import dev.bibikvlad.mastermind.game.printer.MastermindMessagePrinter;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;

public class MastermindGameBootstrap {
    private final LocalizationContext localizationContext;
    private final LogoColorsBundle logoColorsBundle;

    public MastermindGameBootstrap(LocalizationContext localizationContext, LogoColorsBundle logoColorsBundle) {
        this.localizationContext = localizationContext;
        this.logoColorsBundle = logoColorsBundle;
    }

    public GameData launch() {
        GameMessages gameMessages = localizationContext.getGameMessages();
        MastermindMessagePrinter printer = new ConsoleMessagePrinter(gameMessages);
        MastermindUserInputParser parser = new ConsoleInputToLowerCaseParser();

        MastermindConsoleGame game = new MastermindConsoleGame(printer, parser,
                RandomAnswerGenerator.generate(), logoColorsBundle);

        return TimedGameRunner.launch(game);
    }
}
