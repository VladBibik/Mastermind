package dev.bibikvlad.mastermind.input.interpreter;

import dev.bibikvlad.mastermind.input.parser.Parser;

public class PlayerNameInputInterpreter {
    public static PlayerNameInput readSelection(Parser parser) {
        String userInput = parser.parse();

        if (GlobalMenuCommands.EXIT.contains(userInput.trim().toLowerCase())) {
            return new PlayerNameInput(true, userInput);
        } else {
            return new PlayerNameInput(false, userInput);
        }
    }
}
