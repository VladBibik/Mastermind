package dev.bibikvlad.mastermind.menu.core;

import dev.bibikvlad.mastermind.app.context.AppContext;

public abstract class Menu {
    protected final AppContext appContext;

    protected Menu(AppContext appContext) {
        this.appContext = appContext;
    }

    public abstract Menu run();
}
