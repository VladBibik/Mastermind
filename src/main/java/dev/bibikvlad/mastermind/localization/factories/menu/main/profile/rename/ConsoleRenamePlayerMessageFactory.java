package dev.bibikvlad.mastermind.localization.factories.menu.main.profile.rename;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.rename.ConsolePlayerRenameMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.rename.PlayerRenameMenuMessages;

import java.util.ResourceBundle;

public class ConsoleRenamePlayerMessageFactory implements MessageFactory<PlayerRenameMenuMessages> {
    @Override
    public PlayerRenameMenuMessages create(ResourceBundle resourceBundle) {
        return new ConsolePlayerRenameMessages(resourceBundle);
    }
}
