package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.messages.game.ConsoleGameMessages;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessageProviderTest {
    @Test
    @DisplayName("Returns a message instance when the type is registered")
    public void shouldReturnCorrectMessageTypeWhenRegistered() {
        MessageProvider messageProvider =
                new MessageProvider(LocaleType.ENGLISH, MessageRegistryInitializer.createAndPopulateRegistry());
        GameMessages providedMessages =
                messageProvider.getMessages(GameMessages.class);

        assertNotNull(providedMessages);
    }

    @Test
    @DisplayName("Throws exception when requesting unregistered message type")
    public void shouldThrowIllegalStateExceptionWhenTypeIsNotRegistered() {
        MessageProvider messageProvider =
                new MessageProvider(LocaleType.ENGLISH, MessageRegistryInitializer.createAndPopulateRegistry());

        assertThrows(IllegalStateException.class,
                () -> messageProvider.getMessages(ConsoleGameMessages.class));
    }

    @Test
    @DisplayName("Throws NullPointerException when resource bundle name is null")
    public void shouldThrowNullPointerExceptionWhenMessageTypeIsNull() {
        MessageProvider messageProvider =
                new MessageProvider(LocaleType.ENGLISH, MessageRegistryInitializer.createAndPopulateRegistry());

        assertThrows(NullPointerException.class,
                () -> messageProvider.getMessages(null));
    }
}
