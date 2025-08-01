package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.game.parser.BufferedReaderInputParser;
import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.game.printer.MastermindMessagePrinter;
import dev.bibikvlad.mastermind.game.printer.StreamMessagePrinter;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MastermindConsoleGameTest {
    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;
    private GameMessages gameMessages;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        gameMessages = new LocalizationContext(LocaleType.ENGLISH).getGameMessages();
    }

    @Test
    @DisplayName("Invalid input test")
    public void invalidInputTest() {
        String output = runGameAndGetOutputStreamString("rrrr", "abcd\nclose\n");

        assertTrue(output.contains(gameMessages.getInvalidInputMessage()));
    }

    @Test
    @DisplayName("Correct answer on the first attempt")
    public void firstAttemptCorrectAnswerTest() {
        String answer = "yypw";
        String output = runGameAndGetOutputStreamString(answer, "yypw");

        assertTrue(output.contains(gameMessages.getWinMessage(answer)));
    }

    @Test
    @DisplayName("Correct answer on the first attempt")
    public void tenIncorrectAttemptsInARowTest() {
        String answer = "yypw";
        String output = runGameAndGetOutputStreamString(answer, "rrrr\n".repeat(10));

        assertTrue(output.contains(gameMessages.getGameOverMessage(answer)));
    }

    @Test
    @DisplayName("Correct answer after several incorrect attempts")
    public void correctAnswerAfterSeveralIncorrectAttemptsTest() {
        String answer = "bgpw";
        String output = runGameAndGetOutputStreamString(answer, "yypw\nrrbb\npwbg\nbgpw");

        assertTrue(output.contains(gameMessages.getWinMessage(answer)));
    }

    @Test
    @DisplayName("Close on first attempt")
    public void firstAttemptCloseTest() {
        assertDoesNotThrow(
                () -> runGameAndGetOutputStreamString("yrgw", "close"));
    }

    private String runGameAndGetOutputStreamString(String answer, String input) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        MastermindUserInputParser inputParser = new BufferedReaderInputParser(bufferedReader);
        MastermindMessagePrinter messagePrinter = new StreamMessagePrinter(gameMessages, printStream);

        MastermindConsoleGame game = new MastermindConsoleGame(messagePrinter, inputParser, answer);
        game.play();

        return outputStream.toString();
    }
}
