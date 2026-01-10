package dev.bibikvlad.mastermind.menu.games;

import dev.bibikvlad.mastermind.app.bootstrap.LeaderboardServiceGeneratorTEMP;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.persistence.leaderboard.model.MainLeaderboardEntry;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.services.LeaderboardService;
import dev.bibikvlad.utils.formatters.TimeToStringFormatter;

import java.util.List;
import java.util.Optional;

public class LeaderboardMenu implements Menu {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final LeaderboardService leaderboardService;
    private final Player currentPlayer;

    public LeaderboardMenu(LocalizationContext localizationContext, MastermindUserInputParser parser,
                           Player currentPlayer) {
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.leaderboardService = LeaderboardServiceGeneratorTEMP.get();
        this.currentPlayer = currentPlayer;
    }

    @Override
    public Menu run() {
        displayMenu();

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);


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

    private Menu getLeaderboard(long playerId) {
        Optional<List<MainLeaderboardEntry>> optionalLeaderboard = leaderboardService.getMainLeaderboard(playerId);

        if (optionalLeaderboard.isEmpty()) {
            System.out.println("No leaderboard found. Please play some games to build the leaderboard");

            return this;
        }

        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            System.out.println(leaderboardEntry.playerName() + ": " + leaderboardEntry.numberOfTurns() + " turns, "
                    + TimeToStringFormatter.format(leaderboardEntry.gameDuration()));
        });

        return this;
    }
}
