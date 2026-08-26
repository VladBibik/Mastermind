package dev.bibikvlad.mastermind.localization.messages.game;

import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;
import dev.bibikvlad.utils.strings.clue.ClueSymbols;

public interface GameMessages extends LocalizedMessages {
    String getInvalidInput(String validSymbols);

    String getIncorrectGuess(ClueSymbols clueSymbols, int maxTurns, int currentTurn,
                             String answer, String userInput);

    String getGameOver(String answer);

    String getWin(String answer);

    String getRules(ClueSymbols clueSymbols, String validSymbols);
}
