package dev.bibikvlad.mastermind.localization.factories;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.menu.game.ConsoleGameMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.game.GameMenuMessages;

import java.util.ResourceBundle;

public class ConsoleGameMenuMessageFactory implements MessageFactory<GameMenuMessages> {
    @Override
    public GameMenuMessages create(ResourceBundle resourceBundle) {
        return new ConsoleGameMenuMessages(resourceBundle);
    }
}
