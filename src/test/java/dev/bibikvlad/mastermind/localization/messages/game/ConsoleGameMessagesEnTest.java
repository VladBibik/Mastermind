package dev.bibikvlad.mastermind.localization.messages.game;

import dev.bibikvlad.mastermind.app.game.mode.DefaultModeMessages;
import dev.bibikvlad.mastermind.app.game.mode.GameModeDependentMessages;
import dev.bibikvlad.mastermind.clues.ClueGenerator;
import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.mastermind.localization.config.LocalizationType;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.utils.strings.ConsoleColoredValidSymbols;
import dev.bibikvlad.utils.strings.clue.Clue;
import dev.bibikvlad.utils.strings.clue.ClueSymbols;
import dev.bibikvlad.utils.strings.logos.ColoredAsciiLogo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleGameMessagesEnTest {
    private ResourceBundle resourceBundle;
    private GameMessages gameMessages;
    private LogoColorsBundle logoColorsBundle;
    private GameModeDependentMessages gameModeDependentMessages;

    @BeforeEach
    void setUp() {
        resourceBundle = ResourceBundle.getBundle("i18n.game.game_messages",
                LocalizationType.ENGLISH.getLocale());
        gameMessages = new ConsoleGameMessages(resourceBundle);
        logoColorsBundle = new LogoColorsBundle(
                ConsoleColor.ORCHID,
                ConsoleColor.ORANGE,
                ConsoleColor.BRIGHT_RED,
                ConsoleColor.BACKGROUND_BLACK
        );
        gameModeDependentMessages = new DefaultModeMessages(logoColorsBundle);
    }

    @Test
    @DisplayName("Returns correct Invalid Input Message String")
    void testInvalidInputMessage() {
        String result = gameMessages.getInvalidInput(gameModeDependentMessages.validSymbols());

        assertEquals(result, getExpectedInvalidInput());
    }

    @Test
    @DisplayName("Returns correct Incorrect Guess Message String")
    void testIncorrectGuessMessage() {
        String result = gameMessages.getIncorrectGuess(gameModeDependentMessages.getClueSymbols(),
                10, 5, "rgby", "rbww");

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
        String result = gameMessages.getRules(gameModeDependentMessages.getClueSymbols(),
                gameModeDependentMessages.validSymbols());

        assertEquals(result, getExpectedRules());
    }

    @Test
    @DisplayName("Returns correct Ascii Logo String")
    void testAsciiLogoString() {
        String expected = ColoredAsciiLogo.getLogo(logoColorsBundle);

        assertEquals(gameModeDependentMessages.getLogo(), expected);
    }

    String getExpectedInvalidInput() {
        return "Invalid guess. Must include only letters: "
                + ConsoleColoredValidSymbols.getSymbols();
    }

    String getExpectedIncorrectGuess() {
        return "Turn 6 of 10.\n" +
                "Your guess: rbww            " + ClueGenerator.generate(gameModeDependentMessages.getClueSymbols(),
                "rgby", "rbww");
    }

    String getExpectedGameOver() {
        return "Game Over! The solution was: "
                + InputVisualRepresentation.getVisualRepresentation("rgby");
    }

    String getExpectedWin() {
        return "You Won! \uD83C\uDF89" +
                "\nYou are the Mastermind!\n" +
                "Solution was: " + InputVisualRepresentation.getVisualRepresentation("rgby");
    }

    String getExpectedRules() {
        ClueSymbols clueSymbols = gameModeDependentMessages.getClueSymbols();

        return "Puzzle contains 4 boxes. Each turn you choose from 6 colors.\n"
                + "Color choices: " + ConsoleColoredValidSymbols.getSymbols() + "\n"
                + "Example turn: ybgr\n"
                + "Response:\n"
                + clueSymbols.getSymbol(Clue.EXACT) + "   correct color in correct position\n"
                + clueSymbols.getSymbol(Clue.PARTIAL) + "   correct color in incorrect position\n"
                + clueSymbols.getSymbol(Clue.NONE) + "   incorrect color\n"
                + "\n"
                + "The order of the response tiles does not necessarily match the colored characters.\n"
                + "Type 'help', or 'rules' to read these instructions again\n"
                + "Type 'close', or 'exit' to quit and show the solution.\n";
    }
}
