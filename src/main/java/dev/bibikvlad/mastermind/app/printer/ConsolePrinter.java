package dev.bibikvlad.mastermind.app.printer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ConsolePrinter implements Printer {
    public void printMessage(String message) {
        byte[] bytes = (message + System.lineSeparator()).getBytes(StandardCharsets.UTF_8);

        try {
            System.out.write(bytes);
            System.out.flush();
        } catch (IOException exception) {
            throw new IllegalStateException("Could not write to console", exception);
        }
    }
}
