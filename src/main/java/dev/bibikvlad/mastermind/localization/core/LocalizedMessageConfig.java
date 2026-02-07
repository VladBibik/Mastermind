package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;

public class LocalizedMessageConfig<T extends LocalizedMessages> {
    private final Class<T> messageType;
    private final String bundleName;
    private final MessageFactory<T> messageFactory;

    public LocalizedMessageConfig(Class<T> messageType, String bundleName, MessageFactory<T> messageFactory) {
        this.messageType = messageType;
        this.bundleName = bundleName;
        this.messageFactory = messageFactory;
    }

    public Class<T> getMessageType() {
        return messageType;
    }

    public String getBundleName() {
        return bundleName;
    }

    public MessageFactory<T> getMessageFactory() {
        return messageFactory;
    }
}
