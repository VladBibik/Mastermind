package dev.bibikvlad.mastermind.localization.messages.game;

import dev.bibikvlad.mastermind.clues.ClueGenerator;
import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.utils.strings.ConsoleColoredValidSymbols;
import dev.bibikvlad.utils.strings.clue.GameCluesConstants;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ConsoleGameMessages implements GameMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleGameMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getInvalidInput() {
        return MessageFormat.format(resourceBundle.getString("invalid_input"),
                ConsoleColoredValidSymbols.getSymbols());
    }

    @Override
    public String getIncorrectGuess(int maxTurns, int currentTurn, String answer, String userInput) {
        return MessageFormat.format(resourceBundle.getString("incorrect_guess"),
                String.valueOf(currentTurn + 1), String.valueOf(maxTurns), userInput,
                ClueGenerator.generate(answer, userInput));
    }

    @Override
    public String getGameOver(String answer) {
        return MessageFormat.format(resourceBundle.getString("game_over"),
                InputVisualRepresentation.getVisualRepresentation(answer));
    }

    @Override
    public String getWin(String answer) {
        return MessageFormat.format(resourceBundle.getString("win"),
                InputVisualRepresentation.getVisualRepresentation(answer));
    }

    @Override
    public String getRules() {
        return MessageFormat.format(resourceBundle.getString("rules"),
                ConsoleColoredValidSymbols.getSymbols(), String.valueOf(GameCluesConstants.CIRCLE_SHADED),
                String.valueOf(GameCluesConstants.CIRCLE_EMPTY), String.valueOf(GameCluesConstants.UNDERSCORE));
    }
}
