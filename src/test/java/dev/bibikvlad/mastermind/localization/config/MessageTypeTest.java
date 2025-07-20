package dev.bibikvlad.mastermind.localization.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTypeTest {
    @Test
    @DisplayName("Returns correct message type from the index")
    void returnsCorrectMessageTypeFromIndex() {
        assertEquals(MessageType.GAME, MessageType.getByIndex(0));
    }
}
