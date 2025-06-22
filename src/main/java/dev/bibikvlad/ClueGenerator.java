package dev.bibikvlad;

import dev.bibikvlad.utils.ClueComparator;

import java.util.stream.Collectors;

import static dev.bibikvlad.utils.GameCluesConstants.*;

public class ClueGenerator {
    public static String evaluate(String answer, String guess) {
        char[] evaluationCharArray = new char[answer.length()];

        for (int i = 0; i < answer.length(); i++) {
            evaluationCharArray[i] = addClue(answer.charAt(i), i, guess);
        }

        return sortAndGenerateClue(evaluationCharArray);
    }

    private static char addClue(char currentChar, int charIndex, String guess) {
        char clue = ' ';

        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == currentChar && i == charIndex) {
                clue = CIRCLE_SHADED;

                break;
            } else if (guess.charAt(i) == currentChar) {
                clue = CIRCLE_EMPTY;
            } else {
                if (clue != CIRCLE_EMPTY)
                    clue = UNDERSCORE;
            }
        }

        return clue;
    }

    private static String sortAndGenerateClue(char[] clueArray) {
        return new String(clueArray).chars()
                .mapToObj(c -> (char) c)
                .sorted(ClueComparator.getComparator())
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}