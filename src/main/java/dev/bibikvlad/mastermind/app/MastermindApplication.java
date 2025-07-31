package dev.bibikvlad.mastermind.app;

import dev.bibikvlad.mastermind.game.MastermindConsoleGame;
import dev.bibikvlad.mastermind.game.RandomAnswerGenerator;
import dev.bibikvlad.mastermind.game.parser.ConsoleInputParser;
import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.game.printer.ConsoleMessagePrinter;
import dev.bibikvlad.mastermind.game.printer.MastermindMessagePrinter;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

public class MastermindApplication {
    public static void main(String[] args) {
        LocalizationContext localizationContext = new LocalizationContext(LocaleType.ENGLISH);
        GameMessages gameMessages = localizationContext.getGameMessages();
        MastermindMessagePrinter printer = new ConsoleMessagePrinter(gameMessages);
        MastermindUserInputParser parser = new ConsoleInputParser();

        MastermindConsoleGame game = new MastermindConsoleGame(printer, parser, RandomAnswerGenerator.generate());

        game.play();
    }
}
