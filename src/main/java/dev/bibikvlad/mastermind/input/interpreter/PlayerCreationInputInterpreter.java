package dev.bibikvlad.mastermind.input.interpreter;

import dev.bibikvlad.mastermind.input.parser.Parser;

public class PlayerCreationInputInterpreter {
    public static PlayerCreationSelection readSelection(Parser parser) {
        String userInput = parser.parseUserInput().trim().toLowerCase();

        if (GlobalMenuCommands.EXIT.contains(userInput)) {
            return new PlayerCreationSelection(true, userInput);
        } else {
            return new PlayerCreationSelection(false, userInput);
        }
    }
}
