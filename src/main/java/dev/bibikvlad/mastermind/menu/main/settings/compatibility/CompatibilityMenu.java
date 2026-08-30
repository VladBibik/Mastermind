package dev.bibikvlad.mastermind.menu.main.settings.compatibility;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.localization.config.LocalizationType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
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
        AppContext updatedAppContext;

        if (isInCompatibilityMode()) {
            updatedAppContext = turnCompatibilityOff();
        } else {
            updatedAppContext = turnCompatibilityOn();
        }

        return new SettingsMenu(updatedAppContext);
    }

    private AppContext turnCompatibilityOn() {
        long playerId = currentPlayer.getId();
        playerService.turnCompatibilityOn(playerId);

        return buildNewAppContext(LocalizationType.COMPATIBILITY);
    }

    private AppContext turnCompatibilityOff() {
        long playerId = currentPlayer.getId();
        playerService.turnCompatibilityOff(playerId);

        return buildNewAppContext(LocalizationType.ENGLISH);
    }

    private boolean isInCompatibilityMode() {
        LocalizationType localizationType = currentPlayer.getPlayerConfig().localizationType();

        return playerService.isInCompatibilityMode(localizationType);
    }

    private AppContext buildNewAppContext(LocalizationType localizationType) {
        Player updatedPlayer = currentPlayer.withLocalizationType(localizationType);
        LocalizationContext updatedLocalizationContext = new LocalizationContext(localizationType);

        return new AppContext(updatedLocalizationContext, appContext.services(), printer,
                appContext.parser(), updatedPlayer);
    }
}
