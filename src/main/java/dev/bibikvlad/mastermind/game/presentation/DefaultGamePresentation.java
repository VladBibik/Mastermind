package dev.bibikvlad.mastermind.game.presentation;

import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.utils.strings.valid.ConsoleColoredValidSymbols;
import dev.bibikvlad.utils.strings.clue.ClueSymbols;
import dev.bibikvlad.utils.strings.clue.DefaultClueSymbols;
import dev.bibikvlad.utils.strings.logos.ColoredAsciiLogo;

public class DefaultGamePresentation implements GamePresentation {
    private final ClueSymbols clueSymbols = new DefaultClueSymbols();
    private final LogoColorsBundle logoColorsBundle;

    public DefaultGamePresentation(LogoColorsBundle logoColorsBundle) {
        this.logoColorsBundle = logoColorsBundle;
    }

    @Override
    public String getLogo() {
        return ColoredAsciiLogo.getLogo(logoColorsBundle);
    }

    @Override
    public String getVisualRepresentation(String answer) {
        return InputVisualRepresentation.getVisualRepresentation(answer);
    }

    @Override
    public ClueSymbols getClueSymbols() {
        return clueSymbols;
    }

    @Override
    public String getValidSymbols() {
        return ConsoleColoredValidSymbols.getSymbols();
    }
}
