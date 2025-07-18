package dev.bibikvlad.mastermind.model.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MastermindColorsTest {
    @Test
    @DisplayName("Correctly returns enum from the index")
    public void validIndexReturnsCorrectColor() {
        assertEquals(MastermindColors.RED, MastermindColors.fromColorIndex(0));
        assertEquals(MastermindColors.GREEN, MastermindColors.fromColorIndex(1));
        assertEquals(MastermindColors.YELLOW, MastermindColors.fromColorIndex(2));
        assertEquals(MastermindColors.BLUE, MastermindColors.fromColorIndex(3));
        assertEquals(MastermindColors.PURPLE, MastermindColors.fromColorIndex(4));
        assertEquals(MastermindColors.WHITE, MastermindColors.fromColorIndex(5));
    }

    @Test
    @DisplayName("Correctly returns enum from the color symbol")
    public void validColorSymbolReturnsCorrectColor() {
        assertEquals(MastermindColors.RED, MastermindColors.fromColorSymbol('r'));
        assertEquals(MastermindColors.GREEN, MastermindColors.fromColorSymbol('g'));
        assertEquals(MastermindColors.YELLOW, MastermindColors.fromColorSymbol('y'));
        assertEquals(MastermindColors.BLUE, MastermindColors.fromColorSymbol('b'));
        assertEquals(MastermindColors.PURPLE, MastermindColors.fromColorSymbol('p'));
        assertEquals(MastermindColors.WHITE, MastermindColors.fromColorSymbol('w'));

        assertEquals(MastermindColors.RED, MastermindColors.fromColorSymbol('R'));
        assertEquals(MastermindColors.GREEN, MastermindColors.fromColorSymbol('G'));
        assertEquals(MastermindColors.YELLOW, MastermindColors.fromColorSymbol('Y'));
        assertEquals(MastermindColors.BLUE, MastermindColors.fromColorSymbol('B'));
        assertEquals(MastermindColors.PURPLE, MastermindColors.fromColorSymbol('P'));
        assertEquals(MastermindColors.WHITE, MastermindColors.fromColorSymbol('W'));
    }

    @Test
    @DisplayName("Throws exception on invalid index")
    public void invalidIndexThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> MastermindColors.fromColorIndex(-1));
        assertThrows(IllegalArgumentException.class, () -> MastermindColors.fromColorIndex(6));
    }
}
