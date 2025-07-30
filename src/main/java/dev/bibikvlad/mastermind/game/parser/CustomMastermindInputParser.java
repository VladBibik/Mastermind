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
        String userInput = "";

        try {
           userInput = bufferedReader.readLine().toLowerCase();
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }

        return userInput;
    }
}
