package dev.bibikvlad.mastermind.input.interpreter;

import dev.bibikvlad.mastermind.input.parser.Parser;

public class PlayerCreationInputInterpreter {
    public static PlayerCreationInput readSelection(Parser parser) {
        String userInput = parser.parseUserInput().trim().toLowerCase();

        if (GlobalMenuCommands.EXIT.contains(userInput)) {
            return new PlayerCreationInput(true, userInput);
        } else {
            return new PlayerCreationInput(false, userInput);
        }
    }
}
