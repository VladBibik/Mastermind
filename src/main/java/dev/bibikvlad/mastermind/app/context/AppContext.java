package dev.bibikvlad.mastermind.app.context;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.model.player.Player;

public final class AppContext {
    private final LocalizationContext localizationContext;
    private final ServiceContainer serviceContainer;
    private final Printer printer;
    private final Parser parser;
    private final Player currentPlayer;

    public AppContext(LocalizationContext localizationContext, ServiceContainer serviceContainer, Printer printer,
                      Parser parser, Player currentPlayer) {
        this.localizationContext = localizationContext;
        this.serviceContainer = serviceContainer;
        this.printer = printer;
        this.parser = parser;
        this.currentPlayer = currentPlayer;
    }

    public ServiceContainer services() {
        return serviceContainer;
    }

    public Printer printer() {
        return printer;
    }

    public Parser parser() {
        return parser;
    }

    public LocalizationContext localizationContext() {
        return localizationContext;
    }

    public Player currentPlayer() {
        return currentPlayer;
    }
}
