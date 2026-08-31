package dev.bibikvlad.mastermind.input.interpreter;

import dev.bibikvlad.mastermind.input.GlobalMenuCommands;
import dev.bibikvlad.mastermind.input.parser.Parser;

public class ProceedInterpreter {
    public static boolean shouldProceed(Parser parser) {
        String input = parser.parse();

        return !GlobalMenuCommands.EXIT.contains(input.trim().toLowerCase());
    }
}
