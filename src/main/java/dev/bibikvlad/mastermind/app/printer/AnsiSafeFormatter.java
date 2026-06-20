package dev.bibikvlad.mastermind.app.printer;

import dev.bibikvlad.mastermind.model.enums.ConsoleColor;

/**
 * Utility class for working with ANSI-formatted console output.
 *
 * <p>Provides helper methods that prevent ANSI color and style sequences
 * from affecting subsequent console output.
 */
public class AnsiSafeFormatter {
    private static final String RESET = ConsoleColor.RESET.getCode();

    /**
     * Appends an ANSI reset sequence to the supplied text.
     *
     * <p>This is primarily used to prevent color leakage when rendering
     * colored text in the console.
     *
     * <p>Note that the ANSI reset sequence contributes to
     * {@link String#length()} even though it is not visible when rendered.
     * This can affect column alignment when using fixed-width formatting
     * such as {@link String#format(String, Object...)}.
     *
     * @param message text to isolate from subsequent console output
     * @return the supplied text followed by an ANSI reset sequence
     */
    public static String isolate(String message) {
        return message + RESET;
    }
}
