package dev.bibikvlad.mastermind.localization.factories.interaction;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.interaction.ConsoleInteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;

import java.util.ResourceBundle;

public class ConsoleInteractionMessageFactory implements MessageFactory<InteractionMessages> {
    @Override
    public InteractionMessages create(ResourceBundle resourceBundle) {
        return new ConsoleInteractionMessages(resourceBundle);
    }
}
