package dev.bibikvlad.mastermind.localization.messages.menu.settings.language;

import java.util.ResourceBundle;

public class ConsoleLanguageSelectionMessages implements LanguageSelectionMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleLanguageSelectionMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getMenuOptions() {
        return resourceBundle.getString("menu_options");
    }

    @Override
    public String getAlreadySelected() {
        return resourceBundle.getString("langauge_already_selected");
    }

    @Override
    public String getLanguageChanged(String languageName) {
        return resourceBundle.getString("success_language_change")
                .replace("LANGUAGE_NAME", languageName);
    }
}
