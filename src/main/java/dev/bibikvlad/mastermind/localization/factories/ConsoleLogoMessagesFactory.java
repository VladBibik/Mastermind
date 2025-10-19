package dev.bibikvlad.mastermind.localization.factories;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.ConsoleLogoMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.LogoMessages;

import java.util.ResourceBundle;

public class ConsoleLogoMessagesFactory implements MessageFactory<LogoMessages> {
    @Override
    public LogoMessages create(ResourceBundle resourceBundle) {
        return new ConsoleLogoMessages(resourceBundle);
    }
}
