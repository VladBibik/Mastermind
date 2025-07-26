package dev.bibikvlad.mastermind.localization.config;

import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessageTypeTest {
    @Test
    @DisplayName("Returns correct message type from the index")
    void returnsCorrectMessageTypeFromIndex() {
        assertEquals(MessageType.GAME, MessageType.fromIndex(0));
    }

    @Test
    @DisplayName("Returns correct message type from message class")
    void returnsCorrectMessageTypeFromMessageClass() {
        assertEquals(MessageType.GAME, MessageType.fromMessageType(GameMessages.class));
    }

    @Test
    @DisplayName("Throws exception on negative message type index")
    void negativeIndexThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> MessageType.fromIndex(-1));
    }

    @Test
    @DisplayName("Throws exception on invalid locale index")
    void invalidIndexThrowsException() {
        for (int i = 1; i < 100; i++) {
            int index = i;

            assertThrows(IllegalArgumentException.class, () -> MessageType.fromIndex(index));
        }
    }

    @Test
    @DisplayName("Returns correct Message Type Index")
    void returnsCorrectMessageTypeIndex() {
        assertEquals(0, MessageType.GAME.getIndex());
    }
}
