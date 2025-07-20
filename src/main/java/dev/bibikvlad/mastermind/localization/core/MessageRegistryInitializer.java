package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.factories.ConsoleGameMessageFactory;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

public class MessageRegistryInitializer {
    public static MessageFactoryRegistry initialize() {
        MessageFactoryRegistry messageFactoryRegistry = new MessageFactoryRegistry();

        messageFactoryRegistry.register(GameMessages.class, new ConsoleGameMessageFactory());

        return messageFactoryRegistry;
    }
}
