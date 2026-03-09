package dev.bibikvlad.mastermind.localization.factories.menu.main.settings;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.ConsoleSettingsMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.SettingsMenuMessages;

import java.util.ResourceBundle;

public class ConsoleSettingsMessageFactory implements MessageFactory<SettingsMenuMessages> {
    @Override
    public SettingsMenuMessages create(ResourceBundle resourceBundle) {
        return new ConsoleSettingsMenuMessages(resourceBundle);
    }
}
