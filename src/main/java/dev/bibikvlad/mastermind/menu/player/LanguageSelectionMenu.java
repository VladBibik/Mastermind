package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.app.bootstrap.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.services.PlayerService;

public class LanguageSelectionMenu extends Menu {
    private final PlayerService playerService;
    private final Printer printer;
    private final Parser parser;

    public LanguageSelectionMenu(AppContext appContext) {
        super(appContext);

        this.playerService = appContext.services().getPlayerService();
        this.printer = appContext.printer();
        this.parser = appContext.parser();
    }

    @Override
    public Menu run() {
        return null;
    }
}
