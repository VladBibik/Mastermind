package dev.bibikvlad.mastermind.localization.messages.menu.main;

import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;

public interface MainMenuMessages extends LocalizedMessages {
    String getWelcomeMessage(String playerName);

    String getMenuOptionsMessage();
}
