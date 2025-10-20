package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
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
        return messageProvider.getMessages(GameMessages.class, MessageType.GAME.getResourceBundleName());
    }

    public LogoMessages getLogoMessages() {
        return messageProvider.getMessages(LogoMessages.class, MessageType.LOGO.getResourceBundleName());
    }

    @SuppressWarnings("unchecked")
    public <T> T getMessages(MessageType messageType) {
        return (T) messageProvider.getMessages(messageType.getMessageType(), messageType.getResourceBundleName());
    }
}
