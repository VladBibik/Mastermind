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
        return null;
    }

    private void displayMenu() {

    }
}
