package dev.bibikvlad.mastermind.localization.messages.menu.main;

import java.util.ResourceBundle;

public class ConsoleMainMenuMessages implements MainMenuMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleMainMenuMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getWelcomeMessage(String playerName) {
        return resourceBundle.getString("welcome_message")
                .replace("{PLAYER_NAME}", playerName);
    }

    @Override
    public String getMenuOptionsMessage() {
        return resourceBundle.getString("menu_options");
    }
}
