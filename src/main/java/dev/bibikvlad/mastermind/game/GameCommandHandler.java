package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.game.output.GameOutput;
import dev.bibikvlad.mastermind.input.GlobalMenuCommands;

public class GameCommandHandler {
    private final GameOutput gameOutput;

    public GameCommandHandler(GameOutput gameOutput) {
        this.gameOutput = gameOutput;
    }

    public boolean handle(String input) {
        input = input.toLowerCase();

        if (GlobalMenuCommands.EXIT.contains(input)) {
            return true;
        }

        if (GlobalMenuCommands.HELP.contains(input)) {
            gameOutput.printRules();

            return false;
        }

        return false;
    }
}
