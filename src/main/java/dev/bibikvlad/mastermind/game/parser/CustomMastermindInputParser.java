package dev.bibikvlad.mastermind.game.parser;

import java.io.BufferedReader;
import java.io.IOException;

public class CustomMastermindInputParser implements MastermindUserInputParser {
    private final BufferedReader bufferedReader;

    protected CustomMastermindInputParser(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    @Override
    public String parseUserInput() {
        try {
           return bufferedReader.readLine().toLowerCase();
        } catch (IOException exception) {
            throw new IllegalStateException("Could not read line from input stream", exception);
        }
    }
}
