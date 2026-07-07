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

class ConsoleGameMessagesRuTest {
    private final ResourceBundle resourceBundle = ResourceBundle
            .getBundle("i18n.game.game_messages", LocaleType.RUSSIAN.getLocale());
    private final GameMessages gameMessages = new ConsoleGameMessages(resourceBundle);

    @Test
    @DisplayName("Returns correct Invalid Input Message String")
    void testInvalidInputMessage() {
        String result = gameMessages.getInvalidInput();
        String expected = "Неверный формат ответа. Ответ должен содержать только буквы: "
                + ConsoleColoredValidSymbols.getSymbols();

        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Returns correct Incorrect Guess Message String")
    void testIncorrectGuessMessage() {
        String result = gameMessages.getIncorrectGuess(10, 5, "rgby", "rbww");
        String expected = "Ход: 6 из 10.\n" +
                "Ваш ответ: rbww            " + ClueGenerator.generate("rgby", "rbww");

        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Returns correct Game Over Message String")
    void testGameOverMessage() {
        String result = gameMessages.getGameOver("rgby");
        String expected = "Game Over! Ответом была комбинация: "
                + InputVisualRepresentation.getVisualRepresentation("rgby");

        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Returns correct Win Message String")
    void testWinMessage() {
        String result = gameMessages.getWin("rgby");
        String expected = "Вы Победили! " + Emojis.CELEBRATION_TADA +
                "\nВы Mastermind!\n" +
                "Ответом была комбинация: " + InputVisualRepresentation.getVisualRepresentation("rgby");

        assertEquals(result, expected);
    }

    @Test
    @DisplayName("Returns correct Rules Message String")
    void testRulesMessage() {
        String result = gameMessages.getRules();
        String expected = "Пазл состоит из 4 ячеек. Каждый ход Вы выбираете из 6 цветов.\n"
                + "Варианты цветов: " + ConsoleColoredValidSymbols.getSymbols() + "\n"
                + "Пример хода: ybgr\n"
                + "Подсказка:\n"
                + GameCluesConstants.CIRCLE_SHADED + "   Правильный цвет в правильном положении\n"
                + GameCluesConstants.CIRCLE_EMPTY + "   Правильный цвет в неправильном положении\n"
                + GameCluesConstants.UNDERSCORE + "   Неправильный цвет\n"
                + "\n"
                + "Порядок символов в подсказке не обязательно совпадает с позицией цвета.\n"
                + "Введите 'help', или 'rules', чтобы снова увидеть правила.\n"
                + "Введите 'close', или 'exit', чтобы выйти из игры и увидеть ответ.\n";

        assertEquals(result, expected);
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
}
