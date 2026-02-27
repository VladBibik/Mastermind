package dev.bibikvlad.mastermind.input.interpreter;

import dev.bibikvlad.mastermind.input.parser.Parser;

import java.util.Optional;

public class MainMenuInputInterpreter {

    public static Optional<Integer> readSelection(Parser parser) {
        String userInput = parser.parseUserInput().trim().toLowerCase();

        if (GlobalMenuCommands.PLAY.contains(userInput)) {
            return Optional.empty();
        }

        if (GlobalMenuCommands.EXIT.contains(userInput)) {
            return Optional.of(1);
        }

        try {
            return Optional.of(Integer.parseInt(userInput));
        } catch (NumberFormatException exception) {
            return Optional.of(-1);
        }
    }
}
