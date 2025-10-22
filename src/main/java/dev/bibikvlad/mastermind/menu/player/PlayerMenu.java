package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;

public class PlayerMenu {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;

    public PlayerMenu(LocalizationContext localizationContext, MastermindUserInputParser parser) {
        this.localizationContext = localizationContext;
        this.parser = parser;
    }
}
