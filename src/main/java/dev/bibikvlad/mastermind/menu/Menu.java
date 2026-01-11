package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.app.bootstrap.ApplicationContext;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;

public abstract class Menu {
    protected final ApplicationContext applicationContext;
    protected final LocalizationContext localizationContext;
    protected final MastermindUserInputParser parser;
    //TODO: Add printer!

    public Menu(ApplicationContext applicationContext, LocalizationContext localizationContext,
                MastermindUserInputParser parser) {
        this.applicationContext = applicationContext;
        this.localizationContext = localizationContext;
        this.parser = parser;
    }

    public abstract Menu run();
}
