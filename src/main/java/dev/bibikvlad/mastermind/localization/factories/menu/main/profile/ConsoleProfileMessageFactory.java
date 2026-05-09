package dev.bibikvlad.mastermind.localization.factories.menu.main.profile;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.ConsoleProfileMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.ProfileMenuMessages;

import java.util.ResourceBundle;

public class ConsoleProfileMessageFactory implements MessageFactory<ProfileMenuMessages> {
    @Override
    public ProfileMenuMessages create(ResourceBundle resourceBundle) {
        return new ConsoleProfileMenuMessages(resourceBundle);
    }
}
