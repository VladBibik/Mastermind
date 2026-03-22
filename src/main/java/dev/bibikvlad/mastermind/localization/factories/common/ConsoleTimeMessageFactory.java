package dev.bibikvlad.mastermind.localization.factories.common;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.common.ConsoleTimeFormattingMessages;
import dev.bibikvlad.mastermind.localization.messages.common.TimeFormattingMessages;

import java.util.ResourceBundle;

public class ConsoleTimeMessageFactory implements MessageFactory<TimeFormattingMessages> {
    @Override
    public TimeFormattingMessages create(ResourceBundle resourceBundle) {
        return new ConsoleTimeFormattingMessages(resourceBundle);
    }
}
