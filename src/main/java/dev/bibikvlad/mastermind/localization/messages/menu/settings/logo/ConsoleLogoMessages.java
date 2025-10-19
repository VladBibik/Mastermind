package dev.bibikvlad.mastermind.localization.messages.menu.settings.logo;

import java.util.ResourceBundle;

public class ConsoleLogoMessages implements LogoMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleLogoMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getColor(String key) {
        return resourceBundle.getString(key);
    }
}
