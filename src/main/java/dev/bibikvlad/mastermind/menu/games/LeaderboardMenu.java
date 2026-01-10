package dev.bibikvlad.mastermind.menu.games;

import dev.bibikvlad.mastermind.app.bootstrap.LeaderboardServiceGeneratorTEMP;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.services.LeaderboardService;

public class LeaderboardMenu implements Menu {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final LeaderboardService leaderboardService;

    public LeaderboardMenu(LocalizationContext localizationContext, MastermindUserInputParser parser) {
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.leaderboardService = LeaderboardServiceGeneratorTEMP.get();
    }

    @Override
    public Menu run() {
        displayMenu();

        return null;
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("1. Leaderboard");
        System.out.println("2. Leaderboard based on time needed for win");
        System.out.println("3. Leaderboard based on number of turns needed for win");
        System.out.println("4. Win percentage leaderboard");
        System.out.println("5. Leaderboard based on the number of wins");
    }
}
