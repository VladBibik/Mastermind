package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.messages.game.ConsoleGameMessages;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessageFactoryRegistryTest {
    private final MessageFactoryRegistry messageFactoryRegistry = new MessageFactoryRegistry();

    @Test
    @DisplayName("Testing correct registration of GameMessages entry")
    public void register_andGetFactory_returnsRegisteredFactory() {
        MessageFactory<GameMessages> gameMessagesFactory = ConsoleGameMessages::new;

        messageFactoryRegistry.register(GameMessages.class, gameMessagesFactory);

        MessageFactory<GameMessages> gameMessagesResult = messageFactoryRegistry.getMessageFactory(GameMessages.class);
        assertSame(gameMessagesFactory, gameMessagesResult);
    }

    @Test
    @DisplayName("Throws IllegalStateException on unknown type parameter")
    public void throwsOnUnknownTypeParameter() {
        MessageFactory<GameMessages> gameMessagesFactory = ConsoleGameMessages::new;

        assertThrows(IllegalStateException.class,
                () -> messageFactoryRegistry.getMessageFactory(MessageFactoryRegistryTest.class));
    }

    @Test
    @DisplayName("Duplicate registration overwrites enty silently")
    public void duplicateRegistrationOverwritesEnty() {
        MessageFactory<GameMessages> originalGameMessagesFactory = ConsoleGameMessages::new;
        MessageFactory<GameMessages> newGameMessagesFactory = ConsoleGameMessages::new;

        messageFactoryRegistry.register(GameMessages.class, originalGameMessagesFactory);
        messageFactoryRegistry.register(GameMessages.class, newGameMessagesFactory);

        assertSame(newGameMessagesFactory, messageFactoryRegistry.getMessageFactory(GameMessages.class));
    }

    @Test
    @DisplayName("Throws IllegalArgumentException on null value")
    public void throwsIllegalArgumentExceptionOnNullValue() {
        MessageFactory<GameMessages> gameMessagesFactory = ConsoleGameMessages::new;

        assertThrows(IllegalArgumentException.class,
                () -> messageFactoryRegistry.register(null, gameMessagesFactory));
    }
}
