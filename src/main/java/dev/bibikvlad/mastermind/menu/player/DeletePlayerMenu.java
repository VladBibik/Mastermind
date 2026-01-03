package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class DeletePlayerMenu implements Menu {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final PlayerService playerService;
    private final Player currentPlayer;

    public DeletePlayerMenu(LocalizationContext localizationContext, MastermindUserInputParser parser,
                            PlayerService playerService) {
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.playerService = playerService;
        this.currentPlayer = playerService.loadLastSelectedPlayer().get();
    }

    @Override
    public Menu run() {
        if (!playerService.isMultiplePlayersRegistered()) {
            System.out.println("Cannot delete a player.");
            System.out.println("Please register at least one more player first.");

            return new PlayerMenu(localizationContext, parser, playerService);
        }

        playerService.deletePlayer(currentPlayer.getId());

        System.out.println("Player with the name: " + currentPlayer.getPlayerName() + " has been deleted.");

        if (!playerService.isMultiplePlayersRegistered()) {
            return new PlayerMenu(localizationContext, parser, playerService);
        }

        //TODO: This def should be changed.
        System.out.println("If you'll close player selection menu, " +
                "previous last selected player will be picked automatically.");

        return new PlayerSelectionMenu(localizationContext, parser, playerService);
    }
}
