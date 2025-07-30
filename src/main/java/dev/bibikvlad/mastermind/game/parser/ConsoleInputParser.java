package dev.bibikvlad.mastermind.game.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleInputParser implements MastermindUserInputParser {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String parseUserInput() {
        try {
            return bufferedReader.readLine().toLowerCase();
        } catch (IOException exception) {
            throw new IllegalStateException("Could not read line from input stream", exception);
        }
    }
}
