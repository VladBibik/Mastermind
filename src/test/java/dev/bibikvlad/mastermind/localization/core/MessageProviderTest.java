package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageProviderTest {
    @Test
    @DisplayName("Returns correct Message Type object from the registry")
    public void getMessages_createsMessages_returnCorrectMessageType() {
        MessageProvider messageProvider =
                new MessageProvider(LocaleType.ENGLISH, MessageRegistryInitializer.createAndPopulateRegistry());
        GameMessages providedMessages =
                messageProvider.getMessages(GameMessages.class, MessageType.GAME.getResourceBundleName());

        assertEquals(GameMessages.class, providedMessages.getClass());
    }
}
