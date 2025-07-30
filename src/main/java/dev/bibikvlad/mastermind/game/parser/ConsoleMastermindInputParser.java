package dev.bibikvlad.mastermind.game.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleMastermindInputParser implements MastermindUserInputParser {
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

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
