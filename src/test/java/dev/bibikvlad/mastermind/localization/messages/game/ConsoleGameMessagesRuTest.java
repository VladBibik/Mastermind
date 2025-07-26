package dev.bibikvlad.mastermind.localization.messages.game;

import dev.bibikvlad.mastermind.clues.ClueGenerator;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.utils.strings.ConsoleColoredValidSymbols;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleGameMessagesRuTest {
    private final ResourceBundle resourceBundle = ResourceBundle.getBundle(
            MessageType.GAME.getResourceBundleName() + "_ru");
    private final GameMessages gameMessages = new ConsoleGameMessages(resourceBundle);

    @Test
    @DisplayName("Returns correct Invalid Input Message String")
    void testInvalidInputMessage() {
        String result = gameMessages.getInvalidInputMessage();
        String expected = "Неверный формат ответа. Ответ должен включать только буквы: "
                + ConsoleColoredValidSymbols.getSymbols();

        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Returns correct Incorrect Guess Message String")
    void testIncorrectGuessMessage() {
        String result = gameMessages.getIncorrectGuessMessage(10, 5, "rgby", "rbww");
        String expected = "Раунд: 6 из 10.\n" +
                "Ваш ответ: rbww            " + ClueGenerator.generate("rgby", "rbww");

        assertEquals(result, expected);
    }
}
