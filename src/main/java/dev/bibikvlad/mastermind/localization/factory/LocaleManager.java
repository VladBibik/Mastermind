package dev.bibikvlad.mastermind.localization.factory;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

public class LocaleManager {
    private final MessageFactoryRegistry messageFactoryRegistry;
    private final MessageProvider messageProvider;

    public LocaleManager(LocaleType localeType) {
        this.messageFactoryRegistry = new MessageFactoryRegistry();
        this.messageProvider = new MessageProvider(localeType, messageFactoryRegistry);

        this.messageFactoryRegistry.register(GameMessages.class, new ConsoleGameMessageFactory());
    }

    public GameMessages getGameMessages() {
        return messageProvider.getMessages(GameMessages.class, "i18n.game_messages");
    }
}
