package dev.bibikvlad.mastermind.localization.messages.menu.main.settings.compatibility;

import java.util.ResourceBundle;

public class ConsoleCompatibilityMenuMessages implements CompatibilityMenuMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleCompatibilityMenuMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getCompatibilityWarning() {
        return resourceBundle.getString("compatibility_warning");
    }
}
