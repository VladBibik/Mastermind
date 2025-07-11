package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.localization.configurations.LocaleType;
import dev.bibikvlad.mastermind.localization.manager.LocaleManager;
import dev.bibikvlad.mastermind.localization.messages.english.EnglishGameLocale;
import dev.bibikvlad.mastermind.localization.messages.GameMessagesLocale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MastermindConsoleGameTest {
    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;
    private GameMessagesLocale gameMessagesLocale;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        gameMessagesLocale = new LocaleManager(LocaleType.ENGLISH).getGameMessagesLocale();
    }

    @Test
    @DisplayName("Invalid input test")
    public void invalidInputTest() {
        String output = runGame("rrrr", "abcd\nclose\n");

        assertTrue(output.contains(gameMessagesLocale.getInvalidInputMessage()));
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

    @Test
    @DisplayName("Correct answer after several incorrect attempts")
    public void correctAnswerAfterSeveralIncorrectAttemptsTest() {
        String output = runGame("bgpw", "yypw\nrrbb\npwbg\nbgpw");

        assertTrue(output.contains("You Won!"));
    }

    @Test
    @DisplayName("Close on first attempt")
    public void firstAttemptCloseTest() {
        assertDoesNotThrow(() -> runGame("yrgw", "close"));
    }

    private String runGame(String answer, String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        MastermindConsoleGame game = new MastermindConsoleGame(gameMessagesLocale, answer, bufferedReader, printStream);
        game.play();

        return outputStream.toString();
    }
}
