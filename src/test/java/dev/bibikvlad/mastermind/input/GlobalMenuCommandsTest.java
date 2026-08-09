package dev.bibikvlad.mastermind.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GlobalMenuCommandsTest {
    @Test
    @DisplayName("Returns correct size of PLAY command set")
    void returnsCorrectSizeOfPLAYCommandSet() {
        int playCommandCollectionExpectedSize = 11;

        assertEquals(playCommandCollectionExpectedSize, GlobalMenuCommands.PLAY.size());
    }

    @Test
    @DisplayName("Returns correct size of EXIT command set")
    void returnsCorrectSizeOfEXITCommandSet() {
        int exitCommandCollectionExpectedSize = 8;

        assertEquals(exitCommandCollectionExpectedSize, GlobalMenuCommands.EXIT.size());
    }
}
