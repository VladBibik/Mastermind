package dev.bibikvlad.mastermind.game;

import dev.bibikvlad.mastermind.app.printer.PrintStreamPrinter;
import dev.bibikvlad.mastermind.game.presentation.GameMessagePrinter;
import dev.bibikvlad.mastermind.input.parser.BufferedReaderInputParser;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

//TODO: Add tags for different parts of the tests. Like "Game"/"Menu" or think about better categorization
public class MastermindConsoleGameTest {
    private ByteArrayOutputStream outputStream;
    private PrintStream printStream;
    private GameMessages gameMessages;
    private LogoColorsBundle logoColorsBundle;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        printStream = new PrintStream(outputStream);
        gameMessages = new LocalizationContext(LocaleType.ENGLISH).getMessages(MessageType.GAME);

        logoColorsBundle = new LogoColorsBundle(
                ConsoleColor.ORCHID,
                ConsoleColor.ORANGE,
                ConsoleColor.BRIGHT_RED,
                ConsoleColor.BACKGROUND_BLACK
        );
    }

    @Test
    @DisplayName("Invalid input test")
    public void invalidInputTest() {
        String output = runGameAndGetOutputStreamString("rrrr", "abcd\nclose\n");

        assertTrue(output.contains(gameMessages.getInvalidInput()));
    }

    @Test
    @DisplayName("Correct answer on the first attempt")
    public void firstAttemptCorrectAnswerTest() {
        String answer = "yypw";
        String output = runGameAndGetOutputStreamString(answer, "yypw");

        assertTrue(output.contains(gameMessages.getWin(answer)));
    }

    @Test
    @DisplayName("10 incorrect attempts in a row")
    public void tenIncorrectAttemptsInARowTest() {
        String answer = "yypw";
        String output = runGameAndGetOutputStreamString(answer, "rrrr\n".repeat(10));

        assertTrue(output.contains(gameMessages.getGameOver(answer)));
    }

    @Test
    @DisplayName("Correct answer after several incorrect attempts")
    public void correctAnswerAfterSeveralIncorrectAttemptsTest() {
        String answer = "bgpw";
        String output = runGameAndGetOutputStreamString(answer, "yypw\nrrbb\npwbg\nbgpw");

        assertTrue(output.contains(gameMessages.getWin(answer)));
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

        Parser inputParser = new BufferedReaderInputParser(bufferedReader);
        GameMessagePrinter messagePrinter = new GameMessagePrinter(
                new PrintStreamPrinter(printStream), gameMessages);

        MastermindConsoleGame game = new MastermindConsoleGame(messagePrinter, inputParser, answer, logoColorsBundle);
        game.play();

        return outputStream.toString();
    }
}
