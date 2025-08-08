package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.game.printer.MastermindMessagePrinter;

public class GameCommandHandler {
    private final MastermindMessagePrinter printer;

    public GameCommandHandler(MastermindMessagePrinter printer) {
        this.printer = printer;
    }

    public boolean handle(String input) {
        switch (input.toLowerCase()) {
            case "close", "exit" -> {
                return true;
            }
            case "help", "rules" -> {
                printer.printRulesMessage();

                return false;
            }
            default -> {
                return false;
            }
        }
    }
}
