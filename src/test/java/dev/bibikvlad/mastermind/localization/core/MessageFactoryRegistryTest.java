package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.messages.game.ConsoleGameMessages;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessageFactoryRegistryTest {
    @Test
    @DisplayName("Testing correct registration of GameMessages entry")
    public void correctRegistrationOfGameMessages() {
        MessageFactoryRegistry messageFactoryRegistry = new MessageFactoryRegistry();
        MessageFactory<GameMessages> gameMessagesFactory = ConsoleGameMessages::new;

        messageFactoryRegistry.register(GameMessages.class, gameMessagesFactory);

        MessageFactory<GameMessages> gameMessagesResult = messageFactoryRegistry.getMessageFactory(GameMessages.class);
        assertSame(gameMessagesFactory, gameMessagesResult);
    }

    @Test
    @DisplayName("Throws IllegalStateException on unknown type parameter")
    public void throwsOnUnknownTypeParameter() {
        MessageFactoryRegistry messageFactoryRegistry = new MessageFactoryRegistry();
        MessageFactory<GameMessages> gameMessagesFactory = ConsoleGameMessages::new;

        assertThrows(IllegalStateException.class,
                () -> messageFactoryRegistry.getMessageFactory(MessageFactoryRegistryTest.class));
    }
}
