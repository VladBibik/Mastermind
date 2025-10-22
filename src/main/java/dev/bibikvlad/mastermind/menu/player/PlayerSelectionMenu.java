package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;

public class PlayerSelectionMenu {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;

    public PlayerSelectionMenu(LocalizationContext localizationContext, MastermindUserInputParser parser) {
        this.localizationContext = localizationContext;
        this.parser = parser;
    }
}
