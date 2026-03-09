package dev.bibikvlad.mastermind.localization.messages.menu.main.settings;

import java.util.ResourceBundle;

public class ConsoleSettingsMenuMessages implements SettingsMenuMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleSettingsMenuMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getMenuOptions() {
        return resourceBundle.getString("menu_options");
    }
}
