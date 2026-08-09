package dev.bibikvlad.mastermind.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalMenuCommandsTest {
    @Test
    @DisplayName("Returns correct size of PLAY command set")
    void returnsCorrectSizeOfPLAYCommandSet() {
        int expectedSize = 11;

        assertEquals(expectedSize, GlobalMenuCommands.PLAY.size());
    }

    @Test
    @DisplayName("Returns correct size of EXIT command set")
    void returnsCorrectSizeOfEXITCommandSet() {
        int expectedSize = 8;

        assertEquals(expectedSize, GlobalMenuCommands.EXIT.size());
    }

    @Test
    @DisplayName("Returns correct size of YES command set")
    void returnsCorrectSizeOfYESCommandSet() {
        int expectedSize = 2;

        assertEquals(expectedSize, GlobalMenuCommands.YES.size());
    }
}
