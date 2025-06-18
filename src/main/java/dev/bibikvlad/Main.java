package dev.bibikvlad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            Pattern pattern = Pattern.compile("[rgybpw]{4}");

            boolean close = false;
            int counter = 0;
            String answer = RandomAnswerGenerator.generate();

            System.out.println(answer);

            while (!close) {
                if (counter == 10) {
                    System.out.println(answer);
                    System.out.println("You lose");

                    close = true;

                    continue;
                }

                String line = bufferedReader.readLine();

                if (line.equals("close")) {
                    close = true;

                    continue;
                }

                if (line.matches(pattern.pattern())) {
                    counter++;

                    if (line.equals(answer)) {
                        System.out.println("You Won!");

                        close = true;

                        continue;
                    }

                    System.out.println("Step:" + counter);
                } else {
                    System.out.println("Please provide a valid input");
                }
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
