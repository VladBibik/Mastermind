package dev.bibikvlad.mastermind.localization.messages.game;

import dev.bibikvlad.mastermind.app.game.mode.DefaultGamePresentation;
import dev.bibikvlad.mastermind.app.game.mode.GamePresentation;
import dev.bibikvlad.mastermind.clues.ClueGenerator;
import dev.bibikvlad.mastermind.localization.config.LocalizationType;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.utils.strings.ConsoleColoredValidSymbols;
import dev.bibikvlad.utils.strings.clue.Clue;
import dev.bibikvlad.utils.strings.clue.ClueSymbols;
import dev.bibikvlad.utils.strings.logos.ColoredAsciiLogo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleGameMessagesRuTest {
    private final ResourceBundle resourceBundle = ResourceBundle
            .getBundle("i18n.game.game_messages", LocalizationType.RUSSIAN.getLocale());
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
        String result = gameMessages.getIncorrectGuess(gamePresentation.getClueSymbols(),
                10, 5, "rgby", "rbww");

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
        String result = ColoredAsciiLogo.getLogo(logoColorsBundle);
        String expected = ColoredAsciiLogo.getLogo(logoColorsBundle);

        assertEquals(result, expected);
    }

    String getExpectedInvalidInput() {
        return "Неверный формат ответа. Ответ должен содержать только буквы: "
                + ConsoleColoredValidSymbols.getSymbols();
    }

    String getExpectedIncorrectGuess() {
        return "Ход: 6 из 10.\n" +
                "Ваш ответ: rbww            " + ClueGenerator.generate(gamePresentation.getClueSymbols(),
                "rgby", "rbww");
    }

    String getExpectedGameOver() {
        return "Game Over! Ответом была комбинация: "
                + gamePresentation.getVisualRepresentation("rgby");
    }

    String getExpectedWin() {
        return "Вы победили! \uD83C\uDF89" +
                "\nВы Mastermind!\n" +
                "Решением была комбинация: " + gamePresentation.getVisualRepresentation("rgby");
    }

    String getExpectedRules() {
        ClueSymbols clueSymbols = gamePresentation.getClueSymbols();

        return "Пазл состоит из 4 ячеек. Каждый ход Вы выбираете из 6 цветов.\n"
                + "Варианты цветов: " + ConsoleColoredValidSymbols.getSymbols() + "\n"
                + "Пример хода: ybgr\n"
                + "Подсказка:\n"
                + clueSymbols.getSymbol(Clue.EXACT) + "   Правильный цвет в правильном положении\n"
                + clueSymbols.getSymbol(Clue.PARTIAL) + "   Правильный цвет в неправильном положении\n"
                + clueSymbols.getSymbol(Clue.NONE) + "   Неправильный цвет\n"
                + "\n"
                + "Порядок символов в подсказке не обязательно совпадает с позицией цвета.\n"
                + "Введите 'help', или 'rules', чтобы снова увидеть правила.\n"
                + "Введите 'close', или 'exit', чтобы выйти из игры и увидеть ответ.\n";
    }
}
