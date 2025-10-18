package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.config.LocaleType;

public class LanguageSelectionMenu {
    private final MastermindUserInputParser parser;

    public LanguageSelectionMenu(MastermindUserInputParser parser) {
        this.parser = parser;
    }

    public LocaleType selectLanguage() {
        return null;
    }
}
