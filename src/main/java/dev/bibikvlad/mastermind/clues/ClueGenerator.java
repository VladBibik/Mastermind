package dev.bibikvlad.mastermind.clues;

import dev.bibikvlad.utils.CluePriorityComparator;
import dev.bibikvlad.utils.strings.clue.Clue;
import dev.bibikvlad.utils.strings.clue.ClueSymbols;

import java.util.Arrays;
import java.util.stream.Collectors;

public class ClueGenerator {
    public static String generate(ClueSymbols clueSymbols, String answer, String guess) {
        Clue[] clues = new Clue[answer.length()];
        boolean[] answerUsed = new boolean[answer.length()];
        boolean[] guessUsed = new boolean[answer.length()];

        // Step 1: Exact matches (◍)
        for (int i = 0; i < answer.length(); i++) {
            if (guess.charAt(i) == answer.charAt(i)) {
                answerUsed[i] = true;
                guessUsed[i] = true;

                clues[i] = Clue.EXACT;
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

                    clues[i] = Clue.PARTIAL;
                    break;
                }
            }
        }

        // Step 3: Fill the rest with underscores (_)
        for (int i = 0; i < answer.length(); i++) {
            if (clues[i] == null)
                clues[i] = Clue.NONE;
        }

        return sortAndBuildClue(clues, clueSymbols);
    }

    private static String sortAndBuildClue(Clue[] clues, ClueSymbols clueSymbols) {
        return Arrays.stream(clues)
                .sorted(CluePriorityComparator.BY_PRIORITY)
                .map(clueSymbols::getSymbol)
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}
