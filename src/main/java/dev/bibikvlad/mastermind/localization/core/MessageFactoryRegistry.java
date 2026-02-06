package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;

import java.util.HashMap;
import java.util.Map;

public class MessageFactoryRegistry {
    private final Map<Class<? extends LocalizedMessages>,
            MessageFactory<? extends LocalizedMessages>> messageFactories = new HashMap<>();

    public <T extends LocalizedMessages> void register(Class<T> messageType, MessageFactory<T> messageFactory) {
        if (messageType == null || messageFactory == null)
            throw new IllegalArgumentException("Type and factory cannot be null");

        messageFactories.put(messageType, messageFactory);
    }

    @SuppressWarnings("unchecked")
    public <T extends LocalizedMessages> MessageFactory<T> getMessageFactory(Class<T> messageType) {
        MessageFactory<T> messageFactory = (MessageFactory<T>) messageFactories.get(messageType);

        if (messageFactory == null) {
            throw new IllegalStateException("No factory registered for message type " + messageType.getName());
        }

        return (MessageFactory<T>) messageFactories.get(messageType);
    }
}
