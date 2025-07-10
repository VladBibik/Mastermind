package dev.bibikvlad.mastermind.localization.GameMessages;

import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.utils.StringConstants.Emojis;
import dev.bibikvlad.utils.StringConstants.GameCluesConstants;

public class RussianGameLocale extends GameMessagesLocale {

    @Override
    public String getInvalidInputMessage() {
        return "Неверный формат ответа. Ответ должен включать только буквы: " + getColorChoices();
    }

    @Override
    public String getIncorrectGuessMessage(int maxTurns, int currentTurn, String answer, String userInput) {
        return "Раунд: " + (currentTurn + 1) + " из " + maxTurns
                + ". Ваш ответ: " + userInput
                + "\n" + getGuessVisualRepresentation(answer, userInput);
    }

    @Override
    public String getGameOverMessage(String answer) {
        return "Game Over! Ответом была комбинация: "
                + InputVisualRepresentation.getVisualRepresentation(answer);
    }

    @Override
    public String getWinMessage(String answer) {
        return "Вы Победили!" + Emojis.CELEBRATION_TADA
                + "\nВы Mastermind!"
                + "\nОтветом была комбинация: " + InputVisualRepresentation.getVisualRepresentation(answer);
    }

    @Override
    public String getRulesMessage() {
        return """
                Пазл состоит из 4 ячеек. Каждый ход Вы выбираете из 6 цветов.
                Варианты цветов: %s
                Пример хода: ybgr
                Подсказка:
                %s\tПравильный цвет в правильном положении
                %s\tПравильный цвет в неправильном положении
                %s\tНеправильный цвет
                
                Порядок символов в подсказке не обязательно совпадает с позицией цвета.
                Введите 'help', или 'rules', чтобы снова увидеть правила.
                Введите 'close', или 'exit', чтобы выйти из игры и увидеть ответ.
                """
                .formatted(
                        getColorChoices(),
                        GameCluesConstants.CIRCLE_SHADED,
                        GameCluesConstants.CIRCLE_EMPTY,
                        GameCluesConstants.UNDERSCORE
                );
    }
}
