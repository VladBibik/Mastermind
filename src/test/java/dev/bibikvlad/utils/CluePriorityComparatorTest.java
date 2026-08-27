package dev.bibikvlad.utils;

import dev.bibikvlad.utils.strings.clue.Clue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CluePriorityComparatorTest {
    @Test
    @DisplayName("Sorted array demonstrates correct priority of the elements")
    void sortedArrayDemonstratesCorrectPriority() {
        Comparator<Clue> cluePriorityComparator = CluePriorityComparator.BY_PRIORITY;

        //◍○__
        List<Clue> expectedSorted = List.of(Clue.EXACT, Clue.PARTIAL, Clue.NONE, Clue.NONE);
        //__◍○
        List<Clue> unsortedInput = List.of(Clue.NONE, Clue.NONE, Clue.EXACT, Clue.PARTIAL);

        assertEquals(expectedSorted, unsortedInput
                .stream()
                .sorted(cluePriorityComparator)
                .toList()
        );
    }
}
