package dev.bibikvlad;

import dev.bibikvlad.utils.ClueComparator;
import dev.bibikvlad.utils.GameCluesConstants;

import java.util.stream.Collectors;

public class ClueGeneratorTwo {
    public static String evaluate(String answer, String guess) {
        char[] clueArray = new char[answer.length()];
        boolean[] answerUsed = new boolean[answer.length()];
        boolean[] guessUsed = new boolean[answer.length()];

        for (int i = 0; i < answer.length(); i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                answerUsed[i] = true;
                guessUsed[i] = true;

                clueArray[i] = GameCluesConstants.CIRCLE_SHADED;
            }
        }

        for (int i = 0; i < guess.length(); i++) {
            if (guessUsed[i])
                continue;

            for (int j = 0; j < answer.length(); j++) {
                if (!answerUsed[j] && guess.charAt(i) == answer.charAt(j)) {
                    answerUsed[j] = true;
                    guessUsed[i] = true;

                    clueArray[i] = GameCluesConstants.CIRCLE_EMPTY;
                    break;
                }
            }
        }

        for (int i = 0; i < answer.length(); i++) {
            if (clueArray[i] == 0)
                clueArray[i] = GameCluesConstants.UNDERSCORE;
        }

        return new String(clueArray).chars()
                .mapToObj(c -> (char) c)
                .sorted(ClueComparator.getComparator())
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
