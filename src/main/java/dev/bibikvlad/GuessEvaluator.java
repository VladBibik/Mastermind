package dev.bibikvlad;

import java.util.Comparator;
import java.util.stream.Collectors;

import static dev.bibikvlad.utils.GameCluesConstants.*;

public class GuessEvaluator {
    public static String evaluate(String answer, String guess) {
        char[] evaluationCharArray = new char[answer.length()];

        for (int i = 0; i < answer.length(); i++) {
            evaluationCharArray[i] = addClue(answer.charAt(i), i, guess);
        }

        return sortAndGenerateClue(evaluationCharArray);
    }

    private static char addClue(char partOfTheAnswer, int index, String guess) {
        char clue = ' ';

        for (int i = 0; i < guess.length(); i++) {
            if (guess.charAt(i) == partOfTheAnswer && i == index) {
                clue = CIRCLE_SHADED;

                break;
            } else if (guess.charAt(i) == partOfTheAnswer) {
                clue = CIRCLE_EMPTY;
            } else {
                if (clue != CIRCLE_EMPTY)
                    clue = UNDERSCORE;
            }
        }

        return clue;
    }

    private static String sortAndGenerateClue(char[] clueArray) {
        Comparator<Character> characterComparator = (first, second) -> {
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

        return new String(clueArray).chars()
                .mapToObj(c -> (char) c)
                .sorted(characterComparator)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}