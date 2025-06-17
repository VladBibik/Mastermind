package dev.bibikvlad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            Pattern pattern = Pattern.compile("[rgybmw]{4}");

            boolean close = false;

            while (!close) {
                String line = bufferedReader.readLine();

                if (line.equals("close")) {
                    close = true;
                }

                System.out.println(line.matches(String.valueOf(pattern)));
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
}
