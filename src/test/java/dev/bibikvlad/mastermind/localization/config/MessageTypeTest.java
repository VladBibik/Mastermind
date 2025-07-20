package dev.bibikvlad.mastermind.localization.config;

import dev.bibikvlad.mastermind.localization.messages.game.ConsoleGameMessages;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MessageTypeTest {
    @Test
    @DisplayName("Returns correct message type from the index")
    void returnsCorrectMessageTypeFromIndex() {
        assertEquals(MessageType.GAME, MessageType.getByIndex(0));
    }

    @Test
    @DisplayName("Returns correct message type from message class")
    void returnsCorrectMessageTypeFromMessageClass() {
        assertEquals(MessageType.GAME, MessageType.getByMessageType(GameMessages.class));
    }
}
