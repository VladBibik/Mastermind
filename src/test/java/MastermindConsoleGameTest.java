import dev.bibikvlad.mastermind.game.MastermindConsoleGame;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MastermindConsoleGameTest {
    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
    }

    @Test
    @DisplayName("Invalid input test")
    public void invalidInputTest() {
        String output = runGame("rrrr", "abcd\nclose\n");

        assertTrue(output.contains("Please provide a valid input"));
    }

    @Test
    @DisplayName("Correct answer on the first attempt")
    public void firstAttemptCorrectAnswerTest() {
        String output = runGame("yypw", "yypw");

        assertTrue(output.contains("You Won!"));
    }

    @Test
    @DisplayName("Correct answer on the first attempt")
    public void tenIncorrectAttemptsInARowTest() {
        String output = runGame("yypw", "rrrr\n".repeat(10));

        assertTrue(output.contains("You lose"));
    }

    private String runGame(String answer, String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        MastermindConsoleGame game = new MastermindConsoleGame(answer, bufferedReader, printStream);
        game.play();

        return outputStream.toString();
    }
}
