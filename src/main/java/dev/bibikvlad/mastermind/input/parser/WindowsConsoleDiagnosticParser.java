package dev.bibikvlad.mastermind.input.parser;

import java.io.Console;
import java.nio.charset.Charset;

public class WindowsConsoleDiagnosticParser implements Parser {
    private final Console console = System.console();

    @Override
    public String parse() {
        if (console == null) {
            throw new IllegalStateException("No console available.");
        }

        System.out.println("console.charset() = " + console.charset());
        System.out.println("Charset.defaultCharset() = " + Charset.defaultCharset());

        return console.readLine();
    }
}
