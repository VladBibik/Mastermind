package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.factories.ConsoleGameMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.ConsoleLogoMessagesFactory;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.LogoMessages;

public class MessageRegistryInitializer {
    public static MessageFactoryRegistry createAndPopulateRegistry() {
        MessageFactoryRegistry messageFactoryRegistry = new MessageFactoryRegistry();

        messageFactoryRegistry.register(GameMessages.class, new ConsoleGameMessageFactory());
        messageFactoryRegistry.register(LogoMessages.class, new ConsoleLogoMessagesFactory());

        return messageFactoryRegistry;
    }
}
