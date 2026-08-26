package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.app.game.mode.GameModeDependentMessages;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.GlobalMenuCommands;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

public class GameCommandHandler {
    private final Printer printer;
    private final GameMessages gameMessages;
    private final GameModeDependentMessages gameModeDependentMessages;

    public GameCommandHandler(Printer printer, GameMessages gameMessages,
                              GameModeDependentMessages gameModeDependentMessages) {
        this.printer = printer;
        this.gameMessages = gameMessages;
        this.gameModeDependentMessages = gameModeDependentMessages;
    }

    public boolean handle(String input) {
        input = input.toLowerCase();

        if (GlobalMenuCommands.EXIT.contains(input)) {
            return true;
        }

        if (GlobalMenuCommands.HELP.contains(input)) {
            printer.printMessage(gameMessages.getRules(gameModeDependentMessages.getClueSymbols(),
                    gameModeDependentMessages.validSymbols()));

            return false;
        }

        return false;
    }
}
