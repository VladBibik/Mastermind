package dev.bibikvlad.mastermind.localization.factories.menu.main.profile.delete;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.delete.ConsoleDeletePlayerMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.delete.DeletePlayerMenuMessages;

import java.util.ResourceBundle;

public class ConsoleDeletePlayerMessageFactory implements MessageFactory<DeletePlayerMenuMessages> {
    @Override
    public DeletePlayerMenuMessages create(ResourceBundle resourceBundle) {
        return new ConsoleDeletePlayerMessages(resourceBundle);
    }
}
