package dev.bibikvlad.mastermind.localization.config;

import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;
import dev.bibikvlad.mastermind.localization.messages.error.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.MainMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.LogoMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.stats.StatsMessages;

public enum MessageType {
    GAME(GameMessages.class),
    ERROR(InteractionMessages.class),
    MAIN_MENU(MainMenuMessages.class),
    STATS(StatsMessages.class),
    LOGO(LogoMessages.class);

    private final Class<? extends LocalizedMessages> messageClass;


    MessageType(Class<? extends LocalizedMessages> messageClass) {
        this.messageClass = messageClass;
    }

    public Class<? extends LocalizedMessages> getMessageClass() {
        return messageClass;
    }
}
