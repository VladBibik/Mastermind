package dev.bibikvlad.mastermind.localization.messages.game;

import dev.bibikvlad.mastermind.clues.ClueGenerator;
import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.utils.strings.ConsoleColoredValidSymbols;
import dev.bibikvlad.utils.strings.Emojis;
import dev.bibikvlad.utils.strings.GameCluesConstants;
import dev.bibikvlad.utils.strings.logos.ColoredAsciiLogo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleGameMessagesEnTest {
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n.game.game_messages",
            LocaleType.ENGLISH.getLocale());
    private final GameMessages gameMessages = new ConsoleGameMessages(resourceBundle);

    @Test
    @DisplayName("Returns correct Invalid Input Message String")
    void testInvalidInputMessage() {
        String result = gameMessages.getInvalidInput();

        assertEquals(result, getExpectedInvalidInput());
    }

    @Test
    @DisplayName("Returns correct Incorrect Guess Message String")
    void testIncorrectGuessMessage() {
        String result = gameMessages.getIncorrectGuess(10, 5, "rgby", "rbww");

        assertEquals(result, getExpectedIncorrectGuess());
    }

    @Test
    @DisplayName("Returns correct Game Over Message String")
    void testGameOverMessage() {
        String result = gameMessages.getGameOver("rgby");

        assertEquals(result, getExpectedGameOver());
    }

    @Test
    @DisplayName("Returns correct Win Message String")
    void testWinMessage() {
        String result = gameMessages.getWin("rgby");

        assertEquals(result, getExpectedWin());
    }

    @Test
    @DisplayName("Returns correct Rules Message String")
    void testRulesMessage() {
        String result = gameMessages.getRules();

        assertEquals(result, getExpectedRules());
    }

    @Test
    @DisplayName("Returns correct Ascii Logo String")
    void testAsciiLogoString() {
        LogoColorsBundle logoColorsBundle = new LogoColorsBundle(
                ConsoleColor.ORCHID,
                ConsoleColor.ORANGE,
                ConsoleColor.BRIGHT_RED,
                ConsoleColor.BACKGROUND_BLACK
        );

        String result = gameMessages.getAsciiLogo(logoColorsBundle);
        String expected = ColoredAsciiLogo.getLogo(logoColorsBundle);

        assertEquals(result, expected);
    }

    String getExpectedInvalidInput() {
        return "Invalid guess. Must include only letters: "
                + ConsoleColoredValidSymbols.getSymbols();
    }

    String getExpectedIncorrectGuess() {
        return "Turn 6 of 10.\n" +
                "Your guess: rbww            " + ClueGenerator.generate("rgby", "rbww");
    }

    String getExpectedGameOver() {
        return "Game Over! The solution was: "
                + InputVisualRepresentation.getVisualRepresentation("rgby");
    }

    String getExpectedWin() {
        return "You Won! " + Emojis.CELEBRATION_TADA +
                "\nYou are the Mastermind!\n" +
                "Solution was: " + InputVisualRepresentation.getVisualRepresentation("rgby");
    }

    String getExpectedRules() {
        return "Puzzle contains 4 boxes. Each turn you choose from 6 colors.\n"
                + "Color choices: " + ConsoleColoredValidSymbols.getSymbols() + "\n"
                + "Example turn: ybgr\n"
                + "Response:\n"
                + GameCluesConstants.CIRCLE_SHADED + "   correct color in correct position\n"
                + GameCluesConstants.CIRCLE_EMPTY + "   correct color in incorrect position\n"
                + GameCluesConstants.UNDERSCORE + "   incorrect color\n"
                + "\n"
                + "The order of the response tiles does not necessarily match the colored characters.\n"
                + "Type 'help', or 'rules' to read these instructions again\n"
                + "Type 'close', or 'exit' to quit and show the solution.\n";
    }
}
