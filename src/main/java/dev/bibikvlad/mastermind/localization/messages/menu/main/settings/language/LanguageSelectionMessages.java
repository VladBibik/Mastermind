package dev.bibikvlad.mastermind.localization.messages.menu.main.settings.language;

import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;

public interface LanguageSelectionMessages extends LocalizedMessages {
    String getMenuOptions();

    String getAlreadySelected();

    String getLanguageChanged(String languageName);

    String getBackToSettings();
}
