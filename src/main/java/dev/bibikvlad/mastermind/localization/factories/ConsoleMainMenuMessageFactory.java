package dev.bibikvlad.mastermind.localization.factories;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.menu.main.ConsoleMainMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.MainMenuMessages;

import java.util.ResourceBundle;

public class ConsoleMainMenuMessageFactory implements MessageFactory<MainMenuMessages> {
    @Override
    public MainMenuMessages create(ResourceBundle resourceBundle) {
        return new ConsoleMainMenuMessages(resourceBundle);
    }
}
