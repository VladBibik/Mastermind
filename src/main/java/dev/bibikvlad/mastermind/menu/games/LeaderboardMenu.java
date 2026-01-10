package dev.bibikvlad.mastermind.menu.games;

import dev.bibikvlad.mastermind.app.bootstrap.LeaderboardServiceGeneratorTEMP;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.MainMenu;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.persistence.leaderboard.model.*;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.services.LeaderboardService;
import dev.bibikvlad.mastermind.services.PlayerService;
import dev.bibikvlad.utils.formatters.TimeToStringFormatter;

import java.util.List;
import java.util.Optional;

public class LeaderboardMenu implements Menu {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final LeaderboardService leaderboardService;
    private final PlayerService playerService;
    private final Player currentPlayer;

    public LeaderboardMenu(LocalizationContext localizationContext, MastermindUserInputParser parser,
                           PlayerService playerService, Player currentPlayer) {
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.leaderboardService = LeaderboardServiceGeneratorTEMP.get();
        this.playerService = playerService;
        this.currentPlayer = currentPlayer;
    }

    @Override
    public Menu run() {
        displayMenu();

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

        return selection
                .map(this::menuOptionSwitcher)
                .orElseGet(() -> new MainMenu(localizationContext, parser, playerService));
    }

    private void displayMenu() {
        System.out.println();
        System.out.println("1. Leaderboard");
        System.out.println("2. Leaderboard based on time needed for win");
        System.out.println("3. Leaderboard based on number of turns needed for win");
        System.out.println("4. Win percentage leaderboard");
        System.out.println("5. Leaderboard based on the number of wins");
        System.out.println("6: Back to the main menu");
    }

    private Menu menuOptionSwitcher(int userInputNumber) {
        long playerId = currentPlayer.getId();

        switch (userInputNumber) {
            case 1 -> {
                return printLeaderboard(playerId);
            }
            case 2 -> {
                return printTimeLeaderboard(playerId);
            }
            case 3 -> {
                return printTurnsLeaderboard(playerId);
            }
            case 4 -> {
                return printWinPercentageLeaderboard(playerId);
            }
            case 5 -> {
                return printWinsLeaderboard(playerId);
            }
            case 6 -> {
                return quit();
            }
            default -> {
                System.out.println("Invalid input. Please enter a number corresponding to the menu option.");

                return this;
            }
        }
    }

    private Menu printLeaderboard(long playerId) {
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

    private Menu printTimeLeaderboard(long playerId) {
        Optional<List<TimeLeaderboardEntry>> optionalLeaderboard = leaderboardService.getTimeLeaderboard(playerId);

        if (optionalLeaderboard.isEmpty()) {
            System.out.println("No leaderboard found. Please play some games to build the leaderboard");

            return this;
        }

        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            System.out.println(leaderboardEntry.playerName() + ": "
                    + TimeToStringFormatter.format(leaderboardEntry.gameDuration()));
        });

        return this;
    }

    private Menu printTurnsLeaderboard(long playerId) {
        Optional<List<TurnsLeaderboardEntry>> optionalLeaderboard = leaderboardService.getTurnsLeaderboard(playerId);

        if (optionalLeaderboard.isEmpty()) {
            System.out.println("No leaderboard found. Please play some games to build the leaderboard");

            return this;
        }

        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            System.out.println(leaderboardEntry.playerName() + ": "
                    + leaderboardEntry.numberOfTurns() + " turns");
        });

        return this;
    }

    private Menu printWinPercentageLeaderboard(long playerId) {
        Optional<List<WinPercentageLeaderboardEntry>> optionalLeaderboard =
                leaderboardService.getWinPercentageLeaderboard(playerId);

        if (optionalLeaderboard.isEmpty()) {
            System.out.println("No leaderboard found. Please play some games to build the leaderboard");

            return this;
        }

        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            System.out.println(leaderboardEntry.playerName() + ": "
                    + leaderboardEntry.winPercentage() + "%");
        });

        return this;
    }

    private Menu printWinsLeaderboard(long playerId) {
        Optional<List<WinsLeaderboardEntry>> optionalLeaderboard =
                leaderboardService.getWinsLeaderboard(playerId);

        if (optionalLeaderboard.isEmpty()) {
            System.out.println("No leaderboard found. Please play some games to build the leaderboard");

            return this;
        }

        optionalLeaderboard.get().forEach(leaderboardEntry -> {
            System.out.println(leaderboardEntry.playerName() + ": " + leaderboardEntry.numberOfWins());
        });

        return this;
    }

    private Menu quit() {
        return new MainMenu(localizationContext, parser, playerService);
    }
}
