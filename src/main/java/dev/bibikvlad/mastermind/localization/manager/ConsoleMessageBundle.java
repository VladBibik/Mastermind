package dev.bibikvlad.mastermind.localization.manager;

import dev.bibikvlad.mastermind.localization.messages.game.ConsoleGameMessages;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

import java.util.ResourceBundle;

public class ConsoleMessageBundle implements MessageBundle {
    private final GameMessages gameMessages;

    public ConsoleMessageBundle(ResourceBundle resourceBundle) {
        this.gameMessages = new ConsoleGameMessages(resourceBundle);
    }

    public GameMessages getGameMessages() {
        return gameMessages;
    }
}
