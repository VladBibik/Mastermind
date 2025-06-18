package dev.bibikvlad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern pattern = Pattern.compile("[rgybpw]{4}");

    private static boolean close = false;
    private static int turnCounter = 0;

    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            String answer = RandomAnswerGenerator.generate();

            System.out.println(answer);

            while (!close) {
                if (isGameOver(answer)) {
                    continue;
                }

                System.out.println("Turn:" + (turnCounter + 1));

                String line = bufferedReader.readLine();

                if (line.equals("close")) {
                    close = true;

                    continue;
                }

                if (line.matches(pattern.pattern())) {
                    turnCounter++;

                    if (line.equals(answer)) {
                        System.out.println("You Won!");

                        close = true;
                    }
                } else {
                    System.out.println("Please provide a valid input");
                }
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private static boolean isGameOver(String answer) {
        if (turnCounter == 10) {
            System.out.println(answer);
            System.out.println("You lose");

            close = true;

            return true;
        }

        return false;
    }
}
