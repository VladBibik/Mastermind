package dev.bibikvlad.mastermind.input.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LowerCaseParser implements Parser {
    private final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String parse() {
        try {
            return bufferedReader.readLine().toLowerCase();
        } catch (IOException exception) {
            throw new IllegalStateException("Could not read line from input stream", exception);
        }
    }
}
