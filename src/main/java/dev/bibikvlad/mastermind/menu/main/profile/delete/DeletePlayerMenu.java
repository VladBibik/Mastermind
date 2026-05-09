package dev.bibikvlad.mastermind.menu.main.profile.delete;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.profile.ProfileMenu;
import dev.bibikvlad.mastermind.menu.main.profile.selection.PlayerSelectionMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class DeletePlayerMenu extends Menu {
    private final PlayerService playerService;
    private final Player currentPlayer;

    public DeletePlayerMenu(AppContext appContext) {
        super(appContext);

        this.playerService = appContext.services().getPlayerService();
        this.currentPlayer = appContext.currentPlayer();
    }

    @Override
    public Menu run() {
        if (!playerService.isMultiplePlayersRegistered()) {
            System.out.println("Cannot delete a player.");
            System.out.println("Please register at least one more player first.");

            return new ProfileMenu(appContext);
        }

        playerService.deletePlayer(currentPlayer.getId());

        System.out.println("Player with the name: " + currentPlayer.getPlayerName() + " has been deleted.");

        if (!playerService.isMultiplePlayersRegistered()) {
            return new ProfileMenu(appContext);
        }

        //TODO: This doesn't work! You can close PlayerSelectionMenu, and deleted player will be selected. Need a fix!
        System.out.println("If you'll close player selection menu, " +
                "previous last selected player will be picked automatically.");

        return new PlayerSelectionMenu(appContext);
    }
}
