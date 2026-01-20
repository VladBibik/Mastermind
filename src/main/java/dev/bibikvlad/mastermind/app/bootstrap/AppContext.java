package dev.bibikvlad.mastermind.app.bootstrap;

import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.persistence.player.model.Player;

public final class AppContext {
    private final ServiceContainer serviceContainer;
    private final Printer printer;
    private final MastermindUserInputParser parser;

    private LocalizationContext localizationContext;
    private Player currentPlayer;

    public AppContext(ServiceContainer serviceContainer, Printer printer, MastermindUserInputParser parser,
                      LocalizationContext localizationContext) {
        this.serviceContainer = serviceContainer;
        this.printer = printer;
        this.parser = parser;
        this.localizationContext = localizationContext;
    }

    public ServiceContainer services() {
        return serviceContainer;
    }

    public Printer printer() {
        return printer;
    }

    public MastermindUserInputParser parser() {
        return parser;
    }

    public LocalizationContext localizationContext() {
        return localizationContext;
    }

    public void serLocalizationContext(LocalizationContext localizationContext) {
        this.localizationContext = localizationContext;
    }

    public Player currentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}
