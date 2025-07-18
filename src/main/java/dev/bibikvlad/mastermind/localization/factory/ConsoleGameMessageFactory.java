package dev.bibikvlad.mastermind.localization.factory;

import dev.bibikvlad.mastermind.localization.messages.game.ConsoleGameMessages;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

import java.util.ResourceBundle;

public class ConsoleGameMessageFactory implements MessageFactory<GameMessages> {
    @Override
    public GameMessages create(ResourceBundle resourceBundle) {
        return new ConsoleGameMessages(resourceBundle);
    }
}
