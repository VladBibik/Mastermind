package dev.bibikvlad.mastermind.localization.factories.menu.main.profile.name;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.name.ConsolePlayerNameMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.name.PlayerNameMessages;

import java.util.ResourceBundle;

public class ConsolePlayerNameMessageFactory implements MessageFactory<PlayerNameMessages> {
    @Override
    public PlayerNameMessages create(ResourceBundle resourceBundle) {
        return new ConsolePlayerNameMessages(resourceBundle);
    }
}
