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

    private void displayMenu() {
        String menuOptions = """
                Please select a sample size of played games.
                You can select a custom number of games between 10 and 1000.
                
                1. 10 games.
                2. 100 games.
                3. 1000 games.
                0. Back to the leaderboards menu
                """;

        printer.printMessage(menuOptions);
    }
}
