package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

public class LocalizationContext {
    private final MessageProvider messageProvider;

    public LocalizationContext(LocaleType localeType) {
        MessageFactoryRegistry messageFactoryRegistry = MessageRegistryInitializer.createAndPopulateRegistry();
        this.messageProvider = new MessageProvider(localeType, messageFactoryRegistry);
    }

    public GameMessages getGameMessages() {
        return messageProvider.getMessages(GameMessages.class, "i18n.game_messages");
    }

    @SuppressWarnings("unchecked")
    public <T> T getMessages(MessageType messageType) {
        return (T) messageProvider.getMessages(messageType.getMessageType(), messageType.getResourceBundleName());
    }
}
