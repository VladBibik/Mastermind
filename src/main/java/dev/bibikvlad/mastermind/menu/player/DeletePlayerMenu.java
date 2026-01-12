package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class DeletePlayerMenu extends Menu {
    private final PlayerService playerService;
    private final Player currentPlayer;

    public DeletePlayerMenu(LocalizationContext localizationContext, ServiceContainer serviceContainer,
                            MastermindUserInputParser parser) {
        super(localizationContext, serviceContainer, parser);

        this.playerService = serviceContainer.getPlayerService();
        this.currentPlayer = playerService.loadLastSelectedPlayer().orElseThrow(
                () -> new IllegalStateException("No last selected player found!"));
    }

    @Override
    public Menu run() {
        if (!playerService.isMultiplePlayersRegistered()) {
            System.out.println("Cannot delete a player.");
            System.out.println("Please register at least one more player first.");

            return new PlayerMenu(localizationContext, serviceContainer, parser);
        }

        playerService.deletePlayer(currentPlayer.getId());

        System.out.println("Player with the name: " + currentPlayer.getPlayerName() + " has been deleted.");

        if (!playerService.isMultiplePlayersRegistered()) {
            return new PlayerMenu(localizationContext, serviceContainer, parser);
        }

        //TODO: This def should be changed.
        System.out.println("If you'll close player selection menu, " +
                "previous last selected player will be picked automatically.");

        return new PlayerSelectionMenu(localizationContext, serviceContainer, parser);
    }
}
