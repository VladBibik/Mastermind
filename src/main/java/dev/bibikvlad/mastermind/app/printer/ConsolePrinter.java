package dev.bibikvlad.mastermind.app.printer;

public class ConsolePrinter implements Printer {
    public void printMessage(String message) {
        System.out.println(message);
    }
}
