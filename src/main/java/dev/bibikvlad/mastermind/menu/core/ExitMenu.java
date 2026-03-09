package dev.bibikvlad.mastermind.menu.core;

import dev.bibikvlad.mastermind.app.context.AppContext;

public class ExitMenu extends Menu {
    public ExitMenu(AppContext appContext) {
        super(appContext);
    }

    @Override
    public Menu run() {
        return null;
    }
}
