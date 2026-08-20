package dev.bibikvlad.mastermind.app.bootstrap.TEMP_TEST;

import dev.bibikvlad.mastermind.input.parser.ConsoleInputParser;
import dev.bibikvlad.mastermind.input.parser.Parser;

public class RussianCharactersTest {

    public void test() {
        System.out.println("=== INPUT TEST ===");

        Parser parser = new ConsoleInputParser();

        testInput(parser, "Enter Russian text:");
        testInput(parser, "Enter English text:");
    }

    private void testInput(Parser parser, String message) {
        System.out.println(message);

        String input = parser.parse();

        System.out.println("Received: [" + input + "]");
        System.out.println("UTF-16 length: " + input.length());
        System.out.println(
                "Code points: " +
                        input.codePointCount(0, input.length())
        );

        System.out.println("Code points:");

        input.codePoints().forEach(codePoint ->
                System.out.printf("U+%04X%n", codePoint)
        );

        System.out.println();
    }
}
