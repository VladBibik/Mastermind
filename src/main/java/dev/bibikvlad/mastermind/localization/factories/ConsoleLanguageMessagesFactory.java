package dev.bibikvlad.mastermind.localization.factories;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.language.ConsoleLanguageMessages;

import java.util.ResourceBundle;

public class ConsoleLanguageMessagesFactory implements MessageFactory<ConsoleLanguageMessages> {
    @Override
    public ConsoleLanguageMessages create(ResourceBundle resourceBundle) {
        return new ConsoleLanguageMessages(resourceBundle);
    }
}
