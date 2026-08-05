package dev.bibikvlad.mastermind.app.printer;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

public class ConsolePrinter implements Printer {
    private final PrintStream out;

    public ConsolePrinter() {
        this.out = new PrintStream(
                new FileOutputStream(FileDescriptor.out),
                true,
                StandardCharsets.UTF_8
        );
    }

    public void printMessage(String message) {
        out.println(message);
    }
}
