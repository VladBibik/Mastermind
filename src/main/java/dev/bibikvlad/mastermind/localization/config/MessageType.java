package dev.bibikvlad.mastermind.localization.config;

import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

public enum MessageType {
    GAME("i18n.game_messages", GameMessages.class);

    private final String resourceBundleName;
    private final Class<?> messageType;

    MessageType(String resourceBundleName, Class<?> messageType) {
        this.resourceBundleName = resourceBundleName;
        this.messageType = messageType;
    }

    public String getResourceBundleName() {
        return resourceBundleName;
    }

    public Class<?> getMessageType() {
        return messageType;
    }
}
