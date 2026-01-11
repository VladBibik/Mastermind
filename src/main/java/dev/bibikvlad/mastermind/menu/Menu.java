package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;

public abstract class Menu {
    protected final LocalizationContext localizationContext;
    protected final ServiceContainer serviceContainer;
    protected final MastermindUserInputParser parser;
    //TODO: Add printer!

    public Menu(LocalizationContext localizationContext, ServiceContainer serviceContainer,
                MastermindUserInputParser parser) {
        this.localizationContext = localizationContext;
        this.serviceContainer = serviceContainer;
        this.parser = parser;
    }

    public abstract Menu run();
}
