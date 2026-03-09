package dev.bibikvlad.mastermind.localization.factories.menu.main.settings.logo;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.logo.ConsoleLogoColorMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.logo.LogoColorSelectionMessages;

import java.util.ResourceBundle;

public class ConsoleLogoMessageFactory implements MessageFactory<LogoColorSelectionMessages> {
    @Override
    public LogoColorSelectionMessages create(ResourceBundle resourceBundle) {
        return new ConsoleLogoColorMessages(resourceBundle);
    }
}
