package dev.bibikvlad.mastermind.input.parser.factory;

import dev.bibikvlad.mastermind.input.parser.ConsoleInputParser;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.parser.WindowsConsoleInputParser;

public class ParserFactory {
    private ParserFactory() {
        throw new AssertionError(
                "The class ParserFactory cannot be instantiated."
        );
    }

    public static Parser create() {
        if (shouldUseWindowsConsoleInputParser()) {
            return new WindowsConsoleInputParser();
        }

        return new ConsoleInputParser();
    }

    private static boolean shouldUseWindowsConsoleInputParser() {
        return isWindows() && isWindowsConsoleHost();
    }

    private static boolean isWindows() {
        return System.getProperty("os.name")
                .toLowerCase()
                .startsWith("windows");
    }

    private static boolean isWindowsConsoleHost() {
        // TODO: determine the actual terminal/host
        return false;
    }
}