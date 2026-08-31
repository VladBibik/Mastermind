package dev.bibikvlad.mastermind.menu.main.settings.compatibility;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.ProceedInterpreter;
import dev.bibikvlad.mastermind.localization.config.LocalizationType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.compatibility.CompatibilityMenuMessages;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.settings.SettingsMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class CompatibilityMenu extends Menu {
    private final Printer printer;
    private final Player currentPlayer;
    private final PlayerService playerService;
    private final CompatibilityMenuMessages messages;

    public CompatibilityMenu(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.currentPlayer = appContext.currentPlayer();
        this.playerService = appContext.services().getPlayerService();
        this.messages = appContext.localizationContext().getMessages(MessageType.COMPATIBILITY);
    }

    @Override
    public Menu run() {
        printer.printMessage(messages.getCompatibilityWarning());

        if (ProceedInterpreter.shouldProceed(appContext.parser())) {
            return toggleCompatibilityMode();
        }

        return new SettingsMenu(appContext);
    }

    private Menu toggleCompatibilityMode() {
        long playerId = currentPlayer.getId();
        LocalizationType localizationType;

        if (isInCompatibilityMode()) {
            localizationType = playerService.turnCompatibilityOff(playerId);
        } else {
            localizationType = playerService.turnCompatibilityOn(playerId);
        }

        return new SettingsMenu(buildNewAppContext(localizationType));
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
