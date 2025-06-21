package dev.bibikvlad.utils;

import java.util.Comparator;

import static dev.bibikvlad.utils.GameCluesConstants.CIRCLE_EMPTY;
import static dev.bibikvlad.utils.GameCluesConstants.CIRCLE_SHADED;
import static dev.bibikvlad.utils.GameCluesConstants.UNDERSCORE;

public class ClueComparator {
    public static Comparator<Character> getComparator(char[] clueArray) {
        return (first, second) -> {
            if (first == second) {
                return 0;
            } else if (first == CIRCLE_SHADED && (second == UNDERSCORE || second == CIRCLE_EMPTY)) {
                return -1;
            } else if (first == CIRCLE_EMPTY && second == CIRCLE_SHADED) {
                return 1;
            } else if (first == CIRCLE_EMPTY && second == UNDERSCORE) {
                return -1;
            } else {
                return 1;
            }
        };
    }
}
