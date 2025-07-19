package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

public class LocaleManager {
    private final MessageProvider messageProvider;

    public LocaleManager(LocaleType localeType) {
        MessageFactoryRegistry messageFactoryRegistry = new MessageFactoryRegistry();
        this.messageProvider = new MessageProvider(localeType, messageFactoryRegistry);

        MessageRegistryPopulationManager.populate(messageFactoryRegistry);
    }

    public GameMessages getGameMessages() {
        return messageProvider.getMessages(GameMessages.class, "i18n.game_messages");
    }

    public <T> T getMessages(Class<T> clazz, String messageType) {
        return messageProvider.getMessages(clazz, "i18n." + messageType + "_messages");
    }
}
