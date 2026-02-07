package dev.bibikvlad.mastermind.localization.config;

import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.LogoMessages;

public enum MessageType {
    GAME(GameMessages.class),
    LOGO(LogoMessages.class);

    private final Class<? extends LocalizedMessages> messageClass;


    MessageType(Class<? extends LocalizedMessages> messageClass) {
        this.messageClass = messageClass;
    }

    public Class<? extends LocalizedMessages> getMessageClass() {
        return messageClass;
    }
}
