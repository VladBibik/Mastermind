package dev.bibikvlad.mastermind.localization.messages.game;

import dev.bibikvlad.mastermind.clues.ClueGenerator;
import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.utils.strings.ConsoleColoredValidSymbols;
import dev.bibikvlad.utils.strings.Emojis;
import dev.bibikvlad.utils.strings.GameCluesConstants;
import dev.bibikvlad.utils.strings.logos.ColoredAsciiLogo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleGameMessagesEnTest {
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(
            MessageType.GAME.getResourceBundleName());
    private final GameMessages gameMessages = new ConsoleGameMessages(resourceBundle);

    @Test
    @DisplayName("Returns correct Invalid Input Message String")
    void testInvalidInputMessage() {
        String result = gameMessages.getInvalidInputMessage();
        String expected = "Invalid guess. Must include only letters: "
                + ConsoleColoredValidSymbols.getSymbols();

        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Returns correct Incorrect Guess Message String")
    void testIncorrectGuessMessage() {
        String result = gameMessages.getIncorrectGuessMessage(10, 5, "rgby", "rbww");
        String expected = "Turn 6 out of 10.\n" +
                "Your guess: rbww            " + ClueGenerator.generate("rgby", "rbww");

        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Returns correct Game Over Message String")
    void testGameOverMessage() {
        String result = gameMessages.getGameOverMessage("rgby");
        String expected = "Game Over! The solution was: "
                + InputVisualRepresentation.getVisualRepresentation("rgby");

        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Returns correct Win Message String")
    void testWinMessage() {
        String result = gameMessages.getWinMessage("rgby");
        String expected = "You Won! " + Emojis.CELEBRATION_TADA +
                "\nYou are the Mastermind!\n" +
                "Solution was: " + InputVisualRepresentation.getVisualRepresentation("rgby");

        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Returns correct Rules Message String")
    void testRulesMessage() {
        String result = gameMessages.getRulesMessage();
        String expected = "Puzzle contains 4 boxes. Each turn you choose from 6 colors.\n"
                + "Color choices: " + ConsoleColoredValidSymbols.getSymbols() + "\n"
                + "Example turn: ybgr\n"
                + "Response:\n"
                + GameCluesConstants.CIRCLE_SHADED + "   correct color in correct position\n"
                + GameCluesConstants.CIRCLE_EMPTY + "   correct color in incorrect position\n"
                + GameCluesConstants.UNDERSCORE + "   incorrect color\n"
                + "\n"
                + "The order of the response tiles does not necessarily match the colored characters.\n"
                + "Type 'help', or 'rules' to read these instructions again\n"
                + "Type 'close', or 'exit' to quit and show solution.\n";

        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Returns correct Ascii Logo String")
    void testAsciiLogoString() {
        String result = gameMessages.getAsciiLogo();
        String expected = ColoredAsciiLogo.getLogo();

        assertEquals(result, expected);
    }
}
