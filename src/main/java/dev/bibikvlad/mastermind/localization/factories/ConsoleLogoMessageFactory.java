package dev.bibikvlad.mastermind.localization.factories;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.ConsoleLogoColorMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.LogoColorSelectionMessages;

import java.util.ResourceBundle;

public class ConsoleLogoMessageFactory implements MessageFactory<LogoColorSelectionMessages> {
    @Override
    public LogoColorSelectionMessages create(ResourceBundle resourceBundle) {
        return new ConsoleLogoColorMessages(resourceBundle);
    }
}
