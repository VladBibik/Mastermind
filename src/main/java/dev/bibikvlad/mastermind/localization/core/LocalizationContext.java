package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;

public class LocalizationContext {
    private final MessageProvider messageProvider;

    public LocalizationContext(LocaleType localeType) {
        MessageFactoryRegistry messageFactoryRegistry = MessageRegistryInitializer.createAndPopulateRegistry();
        this.messageProvider = new MessageProvider(localeType, messageFactoryRegistry);
    }

    public <T extends LocalizedMessages> T getMessages(Class<T> messageType) {
        return messageProvider.getMessages(messageType);
    }

    @SuppressWarnings("unchecked")
    public <T extends LocalizedMessages> T getMessages(MessageType messageType) {
        return (T) getMessages(messageType.getMessageClass());
    }
}
