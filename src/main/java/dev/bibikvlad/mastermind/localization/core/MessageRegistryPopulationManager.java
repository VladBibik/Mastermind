package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.factories.ConsoleGameMessageFactory;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

public class MessageRegistryPopulationManager {
    public static void populate(MessageFactoryRegistry messageFactoryRegistry) {
        messageFactoryRegistry.register(GameMessages.class, new ConsoleGameMessageFactory());
    }
}
