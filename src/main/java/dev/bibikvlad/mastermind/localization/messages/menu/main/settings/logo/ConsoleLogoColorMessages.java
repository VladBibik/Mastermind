package dev.bibikvlad.mastermind.localization.messages.menu.main.settings.logo;

import java.util.ResourceBundle;

public class ConsoleLogoColorMessages implements LogoColorSelectionMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleLogoColorMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getColorReturn() {
        return resourceBundle.getString("color.return");
    }
}
