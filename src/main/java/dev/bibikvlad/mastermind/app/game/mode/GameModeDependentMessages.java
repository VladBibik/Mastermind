package dev.bibikvlad.mastermind.app.game.mode;

import dev.bibikvlad.utils.strings.clue.ClueSymbols;

public interface GameModeDependentMessages {
    String getLogo();

    String getVisualRepresentation(String answer);

    ClueSymbols getClueSymbols();

    String validSymbols();
}
