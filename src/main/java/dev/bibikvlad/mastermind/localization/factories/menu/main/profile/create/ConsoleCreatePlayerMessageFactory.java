package dev.bibikvlad.mastermind.localization.factories.menu.main.profile.create;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.create.ConsoleNewPlayerCreationMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.create.NewPlayerCreationMenuMessages;

import java.util.ResourceBundle;

public class ConsoleCreatePlayerMessageFactory implements MessageFactory<NewPlayerCreationMenuMessages> {
    @Override
    public NewPlayerCreationMenuMessages create(ResourceBundle resourceBundle) {
        return new ConsoleNewPlayerCreationMessages(resourceBundle);
    }
}
