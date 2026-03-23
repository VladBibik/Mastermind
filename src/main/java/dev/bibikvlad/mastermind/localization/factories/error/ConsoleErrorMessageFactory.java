package dev.bibikvlad.mastermind.localization.factories.error;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.error.ConsoleErrorMessages;
import dev.bibikvlad.mastermind.localization.messages.error.ErrorMessages;

import java.util.ResourceBundle;

public class ConsoleErrorMessageFactory implements MessageFactory<ErrorMessages> {
    @Override
    public ErrorMessages create(ResourceBundle resourceBundle) {
        return new ConsoleErrorMessages(resourceBundle);
    }
}
