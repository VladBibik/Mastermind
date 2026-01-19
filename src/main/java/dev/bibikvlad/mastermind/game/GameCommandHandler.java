package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.game.presentation.GameMessagePrinter;

public class GameCommandHandler {
    private final GameMessagePrinter printer;

    public GameCommandHandler(GameMessagePrinter printer) {
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
