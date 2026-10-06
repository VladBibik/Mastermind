package dev.bibikvlad.utils.strings.valid;

public class ConsoleCompatibleValidSymbols {
    private static final String VALID_SYMBOLS = "r g y b p w";

    private ConsoleCompatibleValidSymbols(){
        throw new AssertionError("ConsoleCompatibleValidSymbols cannot be instantiated.");
    }

    public static String getSymbols() {
        return VALID_SYMBOLS;
    }
}
