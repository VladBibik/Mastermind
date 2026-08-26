package dev.bibikvlad.mastermind.localization.messages.game;

import dev.bibikvlad.mastermind.clues.ClueGenerator;
import dev.bibikvlad.utils.strings.clue.Clue;
import dev.bibikvlad.utils.strings.clue.ClueSymbols;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ConsoleGameMessages implements GameMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleGameMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getInvalidInput(String validSymbols) {
        return MessageFormat.format(resourceBundle.getString("invalid_input"),
                validSymbols);
    }

    @Override
    public String getIncorrectGuess(ClueSymbols clueSymbols, int maxTurns, int currentTurn,
                                    String answer, String userInput) {
        return MessageFormat.format(resourceBundle.getString("incorrect_guess"),
                String.valueOf(currentTurn + 1), String.valueOf(maxTurns), userInput,
                ClueGenerator.generate(clueSymbols, answer, userInput));
    }

    @Override
    public String getGameOver(String visualRepresentation) {
        return MessageFormat.format(resourceBundle.getString("game_over"), visualRepresentation);
    }

    @Override
    public String getWin(String visualRepresentation) {
        return MessageFormat.format(resourceBundle.getString("win"), visualRepresentation);
    }

    @Override
    public String getRules(ClueSymbols clueSymbols, String validSymbols) {
        return MessageFormat.format(resourceBundle.getString("rules"),
                validSymbols, clueSymbols.getSymbol(Clue.EXACT),
                clueSymbols.getSymbol(Clue.PARTIAL), clueSymbols.getSymbol(Clue.NONE));
    }
}
