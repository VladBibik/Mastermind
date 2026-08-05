package dev.bibikvlad.mastermind.app.printer;

import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class ConsolePrinter implements Printer {
    private final PrintStream out;

    public ConsolePrinter() {
        this.out = new PrintStream(
                System.out,
                true,
                StandardCharsets.UTF_8
        );
    }

    public void printMessage(String message) {
        out.println(message);
    }
}
