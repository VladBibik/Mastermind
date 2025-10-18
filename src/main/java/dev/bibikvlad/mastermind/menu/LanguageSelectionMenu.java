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

    private void printMenuOptions() {
        System.out.println("Please select a language");
        System.out.println("To select a language please print a corresponding number, " +
                "or write, for example: 'English'");
        System.out.println("1. English");
        System.out.println("2. Russian");
    }
}
