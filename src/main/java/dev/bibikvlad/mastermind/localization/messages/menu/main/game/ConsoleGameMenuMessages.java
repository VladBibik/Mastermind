package dev.bibikvlad.mastermind.localization.messages.menu.main.game;

import java.util.ResourceBundle;

public class ConsoleGameMenuMessages implements GameMenuMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleGameMenuMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String playAgain() {
        return resourceBundle.getString("play_again");
    }
}
