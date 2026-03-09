package dev.bibikvlad.mastermind.localization.factories.menu.main.settings.logo;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.logo.color.ConsoleColorMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.logo.color.ColorMessages;

import java.util.ResourceBundle;

public class ConsoleColorMessageFactory implements MessageFactory<ColorMessages> {
    @Override
    public ColorMessages create(ResourceBundle resourceBundle) {
        return new ConsoleColorMessages(resourceBundle);
    }
}
