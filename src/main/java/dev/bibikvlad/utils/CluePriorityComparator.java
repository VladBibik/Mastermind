package dev.bibikvlad.utils;

import java.util.Comparator;

import static dev.bibikvlad.utils.GameCluesConstants.CIRCLE_EMPTY;
import static dev.bibikvlad.utils.GameCluesConstants.CIRCLE_SHADED;
import static dev.bibikvlad.utils.GameCluesConstants.UNDERSCORE;

public class CluePriorityComparator {
    public static Comparator<Character> getComparator() {
        return Comparator.comparingInt(CluePriorityComparator::getCluePriority);
    }

    private static int getCluePriority(char clue) {
        return switch (clue) {
            case CIRCLE_SHADED -> 0;
            case CIRCLE_EMPTY -> 1;
            case UNDERSCORE -> 2;
            default -> 3;
        };
    }
}
