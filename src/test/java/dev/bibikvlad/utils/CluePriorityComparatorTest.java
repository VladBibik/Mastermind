package dev.bibikvlad.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CluePriorityComparatorTest {
    @Test
    @DisplayName("Throws illegal argument exception")
    void throwIllegalArgumentException() {
        Comparator<Character> cluePriorityComparator = CluePriorityComparator.BY_PRIORITY;

        List<Character> clueInputs = Arrays.asList('a', 'b');

        assertThrows(IllegalArgumentException.class, () -> clueInputs.stream().sorted(cluePriorityComparator).toList());
    }

    @Test
    @DisplayName("Sorted array demonstrates correct priority of the elements")
    void sortedArrayDemonstratesCorrectPriority() {
        Comparator<Character> cluePriorityComparator = CluePriorityComparator.BY_PRIORITY;

        List<Character> expectedSorted = List.of('◍', '○', '_', '_');
        List<Character> unsortedInput = List.of('_', '_', '◍', '○');

        assertEquals(expectedSorted, unsortedInput.stream().sorted(cluePriorityComparator).toList());
    }
}
