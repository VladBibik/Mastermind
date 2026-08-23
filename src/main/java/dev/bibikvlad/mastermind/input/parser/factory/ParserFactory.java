package dev.bibikvlad.mastermind.input.parser.factory;

import dev.bibikvlad.mastermind.input.parser.ConsoleInputParser;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.parser.WindowsConsoleInputParser;

public class ParserFactory {
    private ParserFactory() {
        throw new AssertionError("The class ParserFactory cannot be instantiated.");
    }

    public static Parser create(boolean nativeConsoleAvailable) {
        if (nativeConsoleAvailable) {
            return new WindowsConsoleInputParser();
        }

        return new ConsoleInputParser();
    }
}