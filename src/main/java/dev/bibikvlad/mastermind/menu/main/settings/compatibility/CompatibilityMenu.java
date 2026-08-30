package dev.bibikvlad.mastermind.menu.main.settings.compatibility;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.settings.SettingsMenu;

public class CompatibilityMenu extends Menu {
    public CompatibilityMenu(AppContext appContext) {
        super(appContext);
    }

    @Override
    public Menu run() {
        return new SettingsMenu(appContext);
    }
}
