package dev.bibikvlad.mastermind.localization.messages.menu.main.settings.logo;

import java.text.MessageFormat;
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

    @Override
    public String getMenuOptions() {
        return resourceBundle.getString("menu.menu_options");
    }

    @Override
    public String getMenuBorderChanged(String preview) {
        return MessageFormat.format(resourceBundle.getString("menu.border_color_changed"), preview);
    }

    @Override
    public String getMenuMainChanged(String preview) {
        return MessageFormat.format(resourceBundle.getString("menu.main_color_changed"), preview);
    }

    @Override
    public String getMenuAccentChanged(String preview) {
        return MessageFormat.format(resourceBundle.getString("menu.accent_color_changed"), preview);
    }

    @Override
    public String getMenuBackgroundChanged(String preview) {
        return MessageFormat.format(resourceBundle.getString("menu.background_color_changed"), preview);
    }
}
