package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;

public class ExitMenu extends Menu {
    public ExitMenu(LocalizationContext localizationContext, ServiceContainer serviceContainer,
                    MastermindUserInputParser parser) {
        super(localizationContext, serviceContainer, parser);
    }

    @Override
    public Menu run() {
        return null;
    }
}
