package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.factories.game.ConsoleGameMessageFactory;
import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessageFactoryRegistryTest {
    private final MessageFactoryRegistry messageFactoryRegistry = new MessageFactoryRegistry();

    @Test
    @DisplayName("Testing correct registration of GameMessages entry")
    public void shouldReturnRegisteredFactoryWhenTypeIsRegistered() {
        LocalizedMessageConfig<GameMessages> localizedMessageConfig = new LocalizedMessageConfig<>(
                GameMessages.class,
                "i18n.game_messages",
                new ConsoleGameMessageFactory()
        );

        messageFactoryRegistry.register(localizedMessageConfig);

        LocalizedMessageConfig<GameMessages> gameMessagesResult =
                messageFactoryRegistry.getLocalizedMessageConfig(GameMessages.class);
        assertSame(localizedMessageConfig, gameMessagesResult);
    }

    @Test
    @DisplayName("Throws IllegalStateException on unknown type parameter")
    public void shouldThrowIllegalStateExceptionWhenTypeIsUnregistered() {
        LocalizedMessages dummyMessages = new LocalizedMessages() {
        };
        assertThrows(IllegalStateException.class,
                () -> messageFactoryRegistry.getLocalizedMessageConfig(dummyMessages.getClass()));
    }

    @Test
    @DisplayName("Duplicate registration overwrites enty silently")
    public void shouldOverwriteExistingFactoryWhenTypeIsRegisteredTwice() {
        LocalizedMessageConfig<GameMessages> originalGameMessagesEntry = new LocalizedMessageConfig<>(
                GameMessages.class,
                "i18n.game_messages",
                new ConsoleGameMessageFactory()
        );
        LocalizedMessageConfig<GameMessages> newGameMessagesEntry = new LocalizedMessageConfig<>(
                GameMessages.class,
                "i18n.game_messages",
                new ConsoleGameMessageFactory()
        );

        messageFactoryRegistry.register(originalGameMessagesEntry);
        messageFactoryRegistry.register(newGameMessagesEntry);

        assertSame(newGameMessagesEntry, messageFactoryRegistry.getLocalizedMessageConfig(GameMessages.class));
    }

    @Test
    @DisplayName("Throws IllegalArgumentException on null value")
    public void shouldThrowIllegalArgumentExceptionWhenTypeIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> messageFactoryRegistry.register(null));
    }
}
