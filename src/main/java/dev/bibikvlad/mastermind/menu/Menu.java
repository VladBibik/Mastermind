package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;

public abstract class Menu {
    protected final ServiceContainer serviceContainer;
    protected final LocalizationContext localizationContext;
    protected final MastermindUserInputParser parser;
    //TODO: Add printer!

    public Menu(ServiceContainer serviceContainer, LocalizationContext localizationContext,
                MastermindUserInputParser parser) {
        this.serviceContainer = serviceContainer;
        this.localizationContext = localizationContext;
        this.parser = parser;
    }

    public abstract Menu run();
}
