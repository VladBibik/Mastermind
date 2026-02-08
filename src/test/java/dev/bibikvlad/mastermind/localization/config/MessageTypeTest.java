package dev.bibikvlad.mastermind.localization.config;

import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessageTypeTest {
    @Test
    @DisplayName("Returns correct message type class")
    void returnsCorrectMessageTypeFromIndex() {
        assertEquals(GameMessages.class, MessageType.GAME.getMessageClass());
    }
}
