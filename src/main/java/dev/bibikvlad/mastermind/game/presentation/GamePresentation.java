package dev.bibikvlad.mastermind.game.presentation;

import dev.bibikvlad.utils.strings.clue.ClueSymbols;

public interface GamePresentation {
    String getLogo();

    String getVisualRepresentation(String answer);

    ClueSymbols getClueSymbols();

    String getValidSymbols();
}
