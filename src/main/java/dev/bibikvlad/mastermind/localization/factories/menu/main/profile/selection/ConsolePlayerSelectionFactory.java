package dev.bibikvlad.mastermind.localization.factories.menu.main.profile.selection;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.selection.ConsolePlayerSelectionMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.selection.PlayerSelectionMenuMessages;

import java.util.ResourceBundle;

public class ConsolePlayerSelectionFactory implements MessageFactory<PlayerSelectionMenuMessages> {
    @Override
    public PlayerSelectionMenuMessages create(ResourceBundle resourceBundle) {
        return new ConsolePlayerSelectionMenuMessages(resourceBundle);
    }
}
