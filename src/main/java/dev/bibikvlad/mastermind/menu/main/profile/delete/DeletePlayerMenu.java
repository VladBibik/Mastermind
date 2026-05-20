package dev.bibikvlad.mastermind.menu.main.profile.delete;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.exceptions.PlayerNotFoundException;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.profile.ProfileMenu;
import dev.bibikvlad.mastermind.menu.main.profile.selection.PlayerSelectionMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

public class DeletePlayerMenu extends Menu {
    private final Printer printer;
    private final Parser parser;
    private final PlayerService playerService;
    private final Player currentPlayer;

    public DeletePlayerMenu(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.playerService = appContext.services().getPlayerService();
        this.currentPlayer = appContext.currentPlayer();
    }

    @Override
    public Menu run() {
        return handlePlayerDeletion();
    }

    private Menu handlePlayerDeletion() {
        playerService.deletePlayer(currentPlayer.getId());

        printer.printMessage("Player with the name: " + currentPlayer.getPlayerName() + " has been deleted.");

        if (!playerService.isMultiplePlayersRegistered()) {
            return new ProfileMenu(createUpdatedAppContext());
        } else {
            printer.printMessage("If you'll close player selection menu, " +
                    "previous last selected player will be picked automatically.");

            return new PlayerSelectionMenu(appContext);
        }
    }

    private AppContext createUpdatedAppContext() {
        Optional<Player> optionalPlayer = playerService.loadLastSelectedPlayer();
        Player player = optionalPlayer.orElseThrow(() -> new PlayerNotFoundException(""));

        return new AppContext(new LocalizationContext(player.getPlayerConfig().locale()),
                this.appContext.services(), this.printer, this.parser, player);
    }
}
