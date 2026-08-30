package dev.bibikvlad.mastermind.menu.main.settings.compatibility;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.settings.SettingsMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class CompatibilityMenu extends Menu {
    private final Printer printer;
    private final Player currentPlayer;
    private final PlayerService playerService;

    public CompatibilityMenu(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.currentPlayer = appContext.currentPlayer();
        this.playerService = appContext.services().getPlayerService();
    }

    @Override
    public Menu run() {
        return new SettingsMenu(appContext);
    }
}
