package dev.bibikvlad.mastermind.localization.messages.menu.main.settings.language;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ConsoleLanguageMessages implements LanguageSelectionMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleLanguageMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getMenuOptions() {
        return resourceBundle.getString("menu_options");
    }

    @Override
    public String getAlreadySelected() {
        return resourceBundle.getString("language_already_selected");
    }

    @Override
    public String getLanguageChanged(String languageName) {
        return MessageFormat.format(resourceBundle.getString("success_language_change"), languageName);
    }

    @Override
    public String getBackToSettings() {
        return resourceBundle.getString("redirect_to_settings");
    }
}
