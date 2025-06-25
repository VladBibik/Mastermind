package dev.bibikvlad.mastermind.clues;

import dev.bibikvlad.utils.GameCluesConstants;

import java.util.stream.Collectors;

import static dev.bibikvlad.utils.CluePriorityComparator.CLUE_COMPARATOR;

public class ClueGenerator {
    public static String generate(String answer, String guess) {
        char[] clueChars = new char[answer.length()];
        boolean[] answerUsed = new boolean[answer.length()];
        boolean[] guessUsed = new boolean[answer.length()];

        for (int i = 0; i < answer.length(); i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                answerUsed[i] = true;
                guessUsed[i] = true;

                clueChars[i] = GameCluesConstants.CIRCLE_SHADED;
            }
        }

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

        for (int i = 0; i < answer.length(); i++) {
            if (clueChars[i] == 0)
                clueChars[i] = GameCluesConstants.UNDERSCORE;
        }

        return sortAndBuildClue(clueChars);
    }

    private static String sortAndBuildClue(char[] clueArray) {
        return new String(clueArray).chars()
                .mapToObj(c -> (char) c)
                .sorted(CLUE_COMPARATOR)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
