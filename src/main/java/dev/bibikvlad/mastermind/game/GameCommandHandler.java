package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.GlobalMenuCommands;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

public class GameCommandHandler {
    private final Printer printer;
    private final GameMessages gameMessages;

    public GameCommandHandler(Printer printer, GameMessages gameMessages) {
        this.printer = printer;
        this.gameMessages = gameMessages;
    }

    public boolean handle(String input) {
        input = input.toLowerCase();

        if (GlobalMenuCommands.EXIT.contains(input)) {
            return true;
        }

        if (GlobalMenuCommands.HELP.contains(input)) {
            printer.printMessage(gameMessages.getRules());

            return false;
        }

        return false;
    }
}
