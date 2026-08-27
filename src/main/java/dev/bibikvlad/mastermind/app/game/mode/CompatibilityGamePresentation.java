package dev.bibikvlad.mastermind.app.game.mode;

import dev.bibikvlad.utils.strings.ConsoleCompatibleValidSymbols;
import dev.bibikvlad.utils.strings.clue.ClueSymbols;
import dev.bibikvlad.utils.strings.clue.CompatibilityClueSymbols;
import dev.bibikvlad.utils.strings.logos.DefaultAsciiLogo;

public class CompatibilityGamePresentation implements GamePresentation {
    @Override
    public String getLogo() {
        return DefaultAsciiLogo.getLogo();
    }

    @Override
    public String getVisualRepresentation(String answer) {
        return answer;
    }

    @Override
    public ClueSymbols getClueSymbols() {
        return new CompatibilityClueSymbols();
    }

    @Override
    public String validSymbols() {
        return ConsoleCompatibleValidSymbols.getSymbols();
    }
}
