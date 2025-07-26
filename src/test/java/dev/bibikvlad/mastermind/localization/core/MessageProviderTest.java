package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.game.ConsoleGameMessages;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessageProviderTest {
    @Test
    @DisplayName("Returns correct Message Type object from the registry")
    public void getMessages_createsMessages_returnCorrectMessageType() {
        MessageProvider messageProvider =
                new MessageProvider(LocaleType.ENGLISH, MessageRegistryInitializer.createAndPopulateRegistry());
        GameMessages providedMessages =
                messageProvider.getMessages(GameMessages.class, MessageType.GAME.getResourceBundleName());

        assertNotNull(providedMessages);
    }

    @Test
    @DisplayName("Throws exception on unregistered message type retrieval")
    public void getMessages_failToCreateMessages_throwsIllegalStateException() {
        MessageProvider messageProvider =
                new MessageProvider(LocaleType.ENGLISH, MessageRegistryInitializer.createAndPopulateRegistry());

        assertThrows(IllegalStateException.class,
                () -> messageProvider.getMessages(ConsoleGameMessages.class, MessageType.GAME.getResourceBundleName()));
    }
}
