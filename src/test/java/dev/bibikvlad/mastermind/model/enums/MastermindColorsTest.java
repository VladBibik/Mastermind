package dev.bibikvlad.mastermind.model.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MastermindColorsTest {
    @Test
    public void validIndexReturnsCorrectColor() {
        assertEquals(MastermindColors.RED, MastermindColors.fromColorIndex(0));
        assertEquals(MastermindColors.GREEN, MastermindColors.fromColorIndex(1));
        assertEquals(MastermindColors.YELLOW, MastermindColors.fromColorIndex(2));
        assertEquals(MastermindColors.BLUE, MastermindColors.fromColorIndex(3));
        assertEquals(MastermindColors.PURPLE, MastermindColors.fromColorIndex(4));
        assertEquals(MastermindColors.WHITE, MastermindColors.fromColorIndex(5));
    }

    @Test
    public void invalidIndexThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> MastermindColors.fromColorIndex(-1));
        assertThrows(IllegalArgumentException.class, () -> MastermindColors.fromColorIndex(6));
    }
}
