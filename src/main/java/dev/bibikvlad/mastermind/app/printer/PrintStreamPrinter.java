package dev.bibikvlad.mastermind.app.printer;

import java.io.PrintStream;

public class PrintStreamPrinter implements Printer {
    private final PrintStream printStream;

    public PrintStreamPrinter(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void printMessage(String message) {
        printStream.println(message);
    }
}
