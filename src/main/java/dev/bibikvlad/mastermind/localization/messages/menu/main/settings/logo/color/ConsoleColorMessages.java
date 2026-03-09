package dev.bibikvlad.mastermind.localization.messages.menu.main.settings.logo.color;

import java.util.ResourceBundle;

public class ConsoleColorMessages implements ColorMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleColorMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getColor(String key) {
        return resourceBundle.getString(key);
    }
}
