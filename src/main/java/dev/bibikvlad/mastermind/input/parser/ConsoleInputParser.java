package dev.bibikvlad.mastermind.input.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ConsoleInputParser implements Parser {
    private final BufferedReader bufferedReader = new BufferedReader(
            new InputStreamReader(System.in, StandardCharsets.UTF_8));

    @Override
    public String parse() {
        try {
            return bufferedReader.readLine();
        } catch (IOException exception) {
            throw new IllegalStateException("Could not read line from input stream", exception);
        }
    }
}
