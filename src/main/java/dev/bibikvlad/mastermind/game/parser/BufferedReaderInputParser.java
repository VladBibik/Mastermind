package dev.bibikvlad.mastermind.game.parser;

import java.io.BufferedReader;
import java.io.IOException;

public class BufferedReaderInputParser implements MastermindUserInputParser {
    private final BufferedReader bufferedReader;

    public BufferedReaderInputParser(BufferedReader bufferedReader) {
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
