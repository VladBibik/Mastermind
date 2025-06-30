package dev.bibikvlad.mastermind.clues;

import dev.bibikvlad.utils.CluePriorityComparator;
import dev.bibikvlad.utils.StringConstants.GameCluesConstants;

import java.util.stream.Collectors;

public class ClueGenerator {
    public static String generate(String answer, String guess) {
        char[] clueChars = new char[answer.length()];
        boolean[] answerUsed = new boolean[answer.length()];
        boolean[] guessUsed = new boolean[answer.length()];

        // Step 1: Exact matches (◍)
        for (int i = 0; i < answer.length(); i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                answerUsed[i] = true;
                guessUsed[i] = true;

                clueChars[i] = GameCluesConstants.CIRCLE_SHADED;
            }
        }

        // Step 2: Partial matches (◯)
        for (int i = 0; i < guess.length(); i++) {
            if (guessUsed[i])
                continue;

            for (int j = 0; j < answer.length(); j++) {
                if (!answerUsed[j] && guess.charAt(i) == answer.charAt(j)) {
                    answerUsed[j] = true;
                    guessUsed[i] = true;

                    clueChars[i] = GameCluesConstants.CIRCLE_EMPTY;
                    break;
                }
            }
        }

        // Step 3: Fill the rest with underscores (_)
        for (int i = 0; i < answer.length(); i++) {
            if (clueChars[i] == 0)
                clueChars[i] = GameCluesConstants.UNDERSCORE;
        }

        return sortAndBuildClue(clueChars);
    }

    private static String sortAndBuildClue(char[] clueArray) {
        return new String(clueArray).chars()
                .mapToObj(c -> (char) c)
                .sorted(CluePriorityComparator.BY_PRIORITY)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
