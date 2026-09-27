package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.config.LocalizationType;
import dev.bibikvlad.mastermind.localization.messages.game.ConsoleGameMessages;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageProviderTest {
    @Test
    @DisplayName("Returns a message instance when the type is registered")
    void shouldReturnCorrectMessageTypeWhenRegistered() {
        MessageProvider messageProvider =
                new MessageProvider(LocalizationType.ENGLISH, MessageRegistryInitializer.createAndPopulateRegistry());
        GameMessages providedMessages =
                messageProvider.getMessages(GameMessages.class);

        assertNotNull(providedMessages);
    }

    @Test
    @DisplayName("Throws exception when requesting unregistered message type")
    void shouldThrowIllegalStateExceptionWhenTypeIsNotRegistered() {
        MessageProvider messageProvider =
                new MessageProvider(LocalizationType.ENGLISH, MessageRegistryInitializer.createAndPopulateRegistry());

        assertThrows(IllegalStateException.class,
                () -> messageProvider.getMessages(ConsoleGameMessages.class));
    }

    @Test
    @DisplayName("Throws NullPointerException when resource bundle name is null")
    void shouldThrowNullPointerExceptionWhenMessageTypeIsNull() {
        MessageProvider messageProvider =
                new MessageProvider(LocalizationType.ENGLISH, MessageRegistryInitializer.createAndPopulateRegistry());

        assertThrows(NullPointerException.class,
                () -> messageProvider.getMessages(null));
    }

    @Test
    @DisplayName("Returns the same message instance on repeated requests")
    void shouldReturnCachedMessageInstance() {
        MessageProvider messageProvider = new MessageProvider(
                LocalizationType.ENGLISH,
                MessageRegistryInitializer.createAndPopulateRegistry()
        );

        GameMessages first = messageProvider.getMessages(GameMessages.class);
        GameMessages second = messageProvider.getMessages(GameMessages.class);

        assertSame(first, second);
    }
}
