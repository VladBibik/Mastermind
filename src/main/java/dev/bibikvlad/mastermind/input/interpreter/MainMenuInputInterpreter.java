package dev.bibikvlad.mastermind.input.interpreter;

import dev.bibikvlad.mastermind.input.GlobalMenuCommands;
import dev.bibikvlad.mastermind.input.parser.Parser;

import java.util.Optional;

public class MainMenuInputInterpreter {
    private MainMenuInputInterpreter() {
        throw new AssertionError("MainMenuInputInterpreter cannot be instantiated");
    }


    public static Optional<Integer> readSelection(Parser parser) {
        String userInput = parser.parse().trim().toLowerCase();

        if (GlobalMenuCommands.EXIT.contains(userInput)) {
            return Optional.empty();
        }

        if (GlobalMenuCommands.PLAY.contains(userInput)) {
            return Optional.of(1);
        }

        try {
            return Optional.of(Integer.parseInt(userInput));
        } catch (NumberFormatException _) {
            return Optional.of(-1);
        }
    }
}
