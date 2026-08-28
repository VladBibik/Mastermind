package dev.bibikvlad.mastermind.localization.messages.game;

import dev.bibikvlad.mastermind.game.presentation.DefaultGamePresentation;
import dev.bibikvlad.mastermind.game.presentation.GamePresentation;
import dev.bibikvlad.mastermind.clues.ClueGenerator;
import dev.bibikvlad.mastermind.localization.config.LocalizationType;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.utils.strings.valid.ConsoleColoredValidSymbols;
import dev.bibikvlad.utils.strings.clue.Clue;
import dev.bibikvlad.utils.strings.clue.ClueSymbols;
import dev.bibikvlad.utils.strings.clue.DefaultClueSymbols;
import dev.bibikvlad.utils.strings.logos.ColoredAsciiLogo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleGameMessagesEnTest {
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle("i18n.game.game_messages",
            LocalizationType.ENGLISH.getLocale());
    private final GameMessages gameMessages = new ConsoleGameMessages(resourceBundle);
    private final LogoColorsBundle logoColorsBundle = new LogoColorsBundle(
            ConsoleColor.ORCHID,
            ConsoleColor.ORANGE,
            ConsoleColor.BRIGHT_RED,
            ConsoleColor.BACKGROUND_BLACK
    );
    private final GamePresentation gamePresentation = new DefaultGamePresentation(logoColorsBundle);

    @Test
    @DisplayName("Returns correct Invalid Input Message String")
    void testInvalidInputMessage() {
        String result = gameMessages.getInvalidInput(gamePresentation.getValidSymbols());

        assertEquals(result, getExpectedInvalidInput());
    }

    @Test
    @DisplayName("Returns correct Incorrect Guess Message String")
    void testIncorrectGuessMessage() {
        int maxTurns = 10;
        int currentTurn = 5;
        String answer = "rgby";
        String guess = "rbww";
        ClueSymbols clueSymbols = new DefaultClueSymbols();
        String generatedClue = ClueGenerator.generate(clueSymbols, answer, guess);

        String result = gameMessages.getIncorrectGuess(maxTurns, currentTurn, answer, guess, generatedClue);

        assertEquals(result, getExpectedIncorrectGuess());
    }

    @Test
    @DisplayName("Returns correct Game Over Message String")
    void testGameOverMessage() {
        String result = gameMessages.getGameOver(gamePresentation.getVisualRepresentation("rgby"));

        assertEquals(result, getExpectedGameOver());
    }

    @Test
    @DisplayName("Returns correct Win Message String")
    void testWinMessage() {
        String result = gameMessages.getWin(gamePresentation.getVisualRepresentation("rgby"));

        assertEquals(result, getExpectedWin());
    }

    @Test
    @DisplayName("Returns correct Rules Message String")
    void testRulesMessage() {
        String result = gameMessages.getRules(gamePresentation.getClueSymbols(),
                gamePresentation.getValidSymbols());

        assertEquals(result, getExpectedRules());
    }

    @Test
    @DisplayName("Returns correct Ascii Logo String")
    void testAsciiLogoString() {
        String expected = ColoredAsciiLogo.getLogo(logoColorsBundle);

        assertEquals(gamePresentation.getLogo(), expected);
    }

    String getExpectedInvalidInput() {
        return "Invalid guess. Must include only letters: "
                + ConsoleColoredValidSymbols.getSymbols();
    }

    String getExpectedIncorrectGuess() {
        return "Turn 6 of 10.\n" +
                "Your guess: rbww            " + ClueGenerator.generate(gamePresentation.getClueSymbols(),
                "rgby", "rbww");
    }

    String getExpectedGameOver() {
        return "Game Over! The solution was: "
                + gamePresentation.getVisualRepresentation("rgby");
    }

    String getExpectedWin() {
        return "You Won! \uD83C\uDF89" +
                "\nYou are the Mastermind!\n" +
                "Solution was: " + gamePresentation.getVisualRepresentation("rgby");
    }

    String getExpectedRules() {
        ClueSymbols clueSymbols = gamePresentation.getClueSymbols();

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
