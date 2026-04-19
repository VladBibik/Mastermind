package dev.bibikvlad.mastermind.menu.main.leaderboards.percentage;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.menu.core.Menu;

public class WinPercentageLeaderboardMenu extends Menu {
    private final Printer printer;
    private final Parser parser;

    public WinPercentageLeaderboardMenu(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
    }

    @Override
    public Menu run() {
        return null;
    }

}
