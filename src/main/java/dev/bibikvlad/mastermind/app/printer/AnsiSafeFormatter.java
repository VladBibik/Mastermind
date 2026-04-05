package dev.bibikvlad.mastermind.app.printer;

import dev.bibikvlad.mastermind.model.enums.ConsoleColor;

public class AnsiSafeFormatter {
    private static final String RESET = ConsoleColor.RESET.getCode();

    public static String isolate(String message) {
        return message + RESET;
    }
}
