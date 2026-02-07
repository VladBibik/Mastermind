package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.LogoMessages;

public class LocalizationContext {
    private MessageProvider messageProvider;
    private MessageFactoryRegistry messageFactoryRegistry;

    public LocalizationContext(LocaleType localeType) {
        this.messageFactoryRegistry = MessageRegistryInitializer.createAndPopulateRegistry();
        this.messageProvider = new MessageProvider(localeType, messageFactoryRegistry);
    }

    public LocalizationContext(MessageProvider messageProvider) {
        this.messageProvider = messageProvider;
    }

    public void changeLocale(LocaleType localeType) {
        messageProvider = new MessageProvider(localeType, messageFactoryRegistry);
    }

    public GameMessages getGameMessages() {
        return messageProvider.getMessages(GameMessages.class);
    }

    public LogoMessages getLogoMessages() {
        return messageProvider.getMessages(LogoMessages.class);
    }

    @SuppressWarnings("unchecked")
    public <T extends LocalizedMessages> T getMessages(MessageType messageType) {
        return (T) messageProvider.getMessages(messageType.getMessageType());
    }
}
