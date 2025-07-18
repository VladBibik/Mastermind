package dev.bibikvlad.mastermind.localization.factory;

import java.util.HashMap;
import java.util.Map;

public class MessageFactoryRegistry {
    private final Map<Class<?>, MessageFactory<?>> messageFactories = new HashMap<>();

    public <T> void register(Class<T> messageType, MessageFactory<T> messageFactory) {
        messageFactories.put(messageType, messageFactory);
    }

    @SuppressWarnings("unchecked")
    public <T> MessageFactory<T> getMessageFactory(Class<T> messageType) {
        return (MessageFactory<T>) messageFactories.get(messageType);
    }
}
