package dev.bibikvlad.mastermind.menu.settings.logo;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;

public class ColorSelectionMenu {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;

    public ColorSelectionMenu(LocalizationContext localizationContext, MastermindUserInputParser parser) {
        this.localizationContext = localizationContext;
        this.parser = parser;
    }
}
