package dev.bibikvlad.utils;

import dev.bibikvlad.utils.strings.clue.Clue;

import java.util.Comparator;

public class CluePriorityComparator {

    private CluePriorityComparator() {
        throw new AssertionError("CluePriorityComparator cannot be instantiated.");
    }

    public static final Comparator<Clue> BY_PRIORITY =
            Comparator.comparingInt(CluePriorityComparator::getCluePriority);

    private static int getCluePriority(Clue clue) {
        return switch (clue) {
            case EXACT -> 0;
            case PARTIAL -> 1;
            case NONE -> 2;
        };
    }
}
