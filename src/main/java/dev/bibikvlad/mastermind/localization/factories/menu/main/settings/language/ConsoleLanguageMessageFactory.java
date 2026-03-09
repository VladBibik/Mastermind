package dev.bibikvlad.mastermind.localization.factories.menu.main.settings.language;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.language.ConsoleLanguageMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.language.LanguageSelectionMessages;

import java.util.ResourceBundle;

public class ConsoleLanguageMessageFactory implements MessageFactory<LanguageSelectionMessages> {
    @Override
    public ConsoleLanguageMessages create(ResourceBundle resourceBundle) {
        return new ConsoleLanguageMessages(resourceBundle);
    }
}
