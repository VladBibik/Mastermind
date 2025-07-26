package dev.bibikvlad.mastermind.model.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @DisplayName("Throws exception on invalid character symbol")
    public void invalidCharacterSymbolThrowsException() {
        List<Character> validCharacters = List.of('r', 'g', 'y', 'b', 'p', 'w', 'R', 'G', 'Y', 'B', 'P', 'W');
        List<Character> characters = new ArrayList<>();

        for (int i = 'a'; i <= 'z'; i++) {
            characters.add((char) i);
        }

        for (int i = 'A'; i <= 'Z'; i++) {
            characters.add((char) i);
        }

        characters.removeAll(validCharacters);

        for (Character character : characters) {
            assertThrows(IllegalArgumentException.class, () -> MastermindColors.fromColorSymbol(character));
        }
    }

    @Test
    @DisplayName("Returns correct Color index")
    public void returnCorrectColorIndex() {
        assertEquals(0, MastermindColors.RED.getColorIndex());
        assertEquals(1, MastermindColors.GREEN.getColorIndex());
        assertEquals(2, MastermindColors.YELLOW.getColorIndex());
        assertEquals(3, MastermindColors.BLUE.getColorIndex());
        assertEquals(4, MastermindColors.PURPLE.getColorIndex());
        assertEquals(5, MastermindColors.WHITE.getColorIndex());
    }
}
