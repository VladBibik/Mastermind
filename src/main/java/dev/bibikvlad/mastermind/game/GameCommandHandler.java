package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.game.printer.MastermindGameMessagePrinter;

public class GameCommandHandler {
    private final MastermindGameMessagePrinter printer;

    public GameCommandHandler(MastermindGameMessagePrinter printer) {
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
