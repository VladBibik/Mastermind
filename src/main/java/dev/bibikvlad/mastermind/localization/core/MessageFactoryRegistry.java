package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;

import java.util.HashMap;
import java.util.Map;

public class MessageFactoryRegistry {
    private final Map<Class<? extends LocalizedMessages>,
            LocalizedMessageConfig<? extends LocalizedMessages>> messageFactories = new HashMap<>();

    public <T extends LocalizedMessages> void register(LocalizedMessageConfig<T> localizedMessageConfig) {
        if (localizedMessageConfig == null)
            throw new IllegalArgumentException("Type and factory cannot be null");

        messageFactories.put(localizedMessageConfig.getMessageType(), localizedMessageConfig);
    }

    @SuppressWarnings("unchecked")
    public <T extends LocalizedMessages> LocalizedMessageConfig<T> getLocalizedMessageConfig(Class<T> messageType) {
        LocalizedMessageConfig<?> localizedMessageConfig = messageFactories.get(messageType);

        if (localizedMessageConfig == null) {
            throw new IllegalStateException("No factory registered for message type " + messageType.getName());
        }

        return (LocalizedMessageConfig<T>) localizedMessageConfig;
    }
}
