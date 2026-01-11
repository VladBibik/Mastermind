package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;

public class ExitMenu extends Menu {
    public ExitMenu(ServiceContainer serviceContainer, LocalizationContext localizationContext,
                    MastermindUserInputParser parser) {
        super(serviceContainer, localizationContext, parser);
    }

    @Override
    public Menu run() {
        return null;
    }
}
