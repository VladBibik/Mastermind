package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;

import java.util.ResourceBundle;

public class MessageProvider {
    private final LocaleType localeType;
    private final MessageFactoryRegistry messageFactoryRegistry;

    public MessageProvider(LocaleType localeType, MessageFactoryRegistry messageFactoryRegistry) {
        this.localeType = localeType;
        this.messageFactoryRegistry = messageFactoryRegistry;
    }

    public <T extends LocalizedMessages> T getMessages(Class<T> messageType) {
        LocalizedMessageConfig<T> localizedMessageConfig =
                messageFactoryRegistry.getLocalizedMessageConfig(messageType);

        ResourceBundle resourceBundle =
                ResourceBundle.getBundle(localizedMessageConfig.getBundleName(), localeType.getLocale());

        return localizedMessageConfig.getMessageFactory().create(resourceBundle);
    }
}
