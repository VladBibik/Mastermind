package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class DeletePlayerMenu {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final PlayerService playerService;
    private final Player currentPlayer;

    public DeletePlayerMenu(LocalizationContext localizationContext, MastermindUserInputParser parser,
                            PlayerService playerService, Player currentPlayer) {
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.playerService = playerService;
        this.currentPlayer = currentPlayer;
    }

    public boolean menu() {
        if (!playerService.isMultiplePlayersRegistered()) {
            System.out.println("Cannot delete a player.");
            System.out.println("Please register at least one player first.");

            return false;
        }

        playerService.deletePlayer(currentPlayer.getId());

        System.out.println("Player with the name: " + currentPlayer.getPlayerName() + " has been deleted.");

        if (!playerService.isMultiplePlayersRegistered()) {
            return true;
        }

        //TODO: This def should be changed.
        System.out.println("If you'll close player selection menu, " +
                "previous last selected player will be picked automatically.");

        PlayerSelectionMenu playerSelectionMenu = new PlayerSelectionMenu(localizationContext, parser, playerService);

        playerSelectionMenu.selectPlayer();

        return true;
    }
}
