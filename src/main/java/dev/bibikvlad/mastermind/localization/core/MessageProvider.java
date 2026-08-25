package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.config.LocalizationType;
import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MessageProvider {
    private final LocalizationType localizationType;
    private final MessageFactoryRegistry messageFactoryRegistry;
    private final Map<Class<? extends LocalizedMessages>, LocalizedMessages> cache = new HashMap<>();

    public MessageProvider(LocalizationType localizationType, MessageFactoryRegistry messageFactoryRegistry) {
        this.localizationType = localizationType;
        this.messageFactoryRegistry = messageFactoryRegistry;
    }

    @SuppressWarnings("unchecked")
    public <T extends LocalizedMessages> T getMessages(Class<T> messageType) {
        if (cache.containsKey(messageType)) {
            return (T) cache.get(messageType);
        } else {
            LocalizedMessageConfig<T> localizedMessageConfig =
                    messageFactoryRegistry.getLocalizedMessageConfig(messageType);

            ResourceBundle resourceBundle =
                    ResourceBundle.getBundle(localizedMessageConfig.getBundleName(), localizationType.getLocale());

            T messages = localizedMessageConfig.getMessageFactory().create(resourceBundle);
            cache.put(messageType, messages);

            return messages;
        }
    }
}
