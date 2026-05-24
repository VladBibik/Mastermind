package dev.bibikvlad.mastermind.localization.messages.menu.main.profile.delete;

import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;

public interface DeletePlayerMenuMessages extends LocalizedMessages {
    String getFirstWarning(String playerName);

    String getDeleteConfirmation(String playerName);

    String getPlayerDeleted(String playerName);

    String getPlayerAutoSelected(String playerName);

    String getAutoSelectionWarning(String playerName);
}
