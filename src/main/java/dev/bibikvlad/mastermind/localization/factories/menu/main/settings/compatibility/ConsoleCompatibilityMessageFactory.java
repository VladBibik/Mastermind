package dev.bibikvlad.mastermind.localization.factories.menu.main.settings.compatibility;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.compatibility.CompatibilityMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.compatibility.ConsoleCompatibilityMenuMessages;

import java.util.ResourceBundle;

public class ConsoleCompatibilityMessageFactory implements MessageFactory<CompatibilityMenuMessages> {
    @Override
    public CompatibilityMenuMessages create(ResourceBundle resourceBundle) {
        return new ConsoleCompatibilityMenuMessages(resourceBundle);
    }
}
