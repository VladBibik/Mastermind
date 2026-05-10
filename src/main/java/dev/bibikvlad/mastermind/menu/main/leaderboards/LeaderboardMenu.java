package dev.bibikvlad.mastermind.menu.main.leaderboards;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.AnsiSafeFormatter;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.app.printer.table.Column;
import dev.bibikvlad.mastermind.app.printer.table.TablePrinter;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.leaderboards.LeaderboardMessages;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.MainMenu;
import dev.bibikvlad.mastermind.menu.main.leaderboards.percentage.WinPercentageLeaderboardMenu;
import dev.bibikvlad.mastermind.model.leaderboard.MainLeaderboardEntry;
import dev.bibikvlad.mastermind.model.leaderboard.TimeLeaderboardEntry;
import dev.bibikvlad.mastermind.model.leaderboard.TurnsLeaderboardEntry;
import dev.bibikvlad.mastermind.model.leaderboard.WinsLeaderboardEntry;
import dev.bibikvlad.mastermind.services.GamesService;
import dev.bibikvlad.mastermind.services.LeaderboardService;
import dev.bibikvlad.utils.formatters.ClockDisplayFormatter;

import java.util.List;
import java.util.Optional;

public class LeaderboardMenu extends Menu {
    private final Printer printer;
    private final Parser parser;
    private final LeaderboardMessages leaderboardMessages;
    private final InteractionMessages interactionMessages;
    private final LeaderboardService leaderboardService;
    private final GamesService gamesService;

    private boolean showMenuOnNextLoop = true;

    public LeaderboardMenu(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.leaderboardMessages = appContext.localizationContext().getMessages(MessageType.LEADERBOARDS);
        this.interactionMessages = appContext.localizationContext().getMessages(MessageType.INTERACTION);
        this.leaderboardService = appContext.services().getLeaderboardService();
        this.gamesService = appContext.services().getGamesService();
    }

    @Override
    public Menu run() {
        if (showMenuOnNextLoop) {
            displayMenu();
            showMenuOnNextLoop = false;
        }

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

        return selection
                .map(this::menuOptionSwitcher)
                .orElseGet(() -> new MainMenu(appContext));
    }

    private void displayMenu() {
        printer.printMessage(leaderboardMessages.getLeaderboardMenuOptions());
    }

    private Menu menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> {
                return printLeaderboard();
            }
            case 2 -> {
                return printTimeLeaderboard();
            }
            case 3 -> {
                return printTurnsLeaderboard();
            }
            case 4 -> {
                return handleWinPercentageSelection();
            }
            case 5 -> {
                return printWinsLeaderboard();
            }
            case 0 -> {
                return quit();
            }
            default -> {
                printer.printMessage(interactionMessages.getInvalidInput());

                return this;
            }
        }
    }

    private Menu printLeaderboard() {
        Optional<List<MainLeaderboardEntry>> optionalLeaderboard = leaderboardService.getMainLeaderboard();

        if (optionalLeaderboard.isEmpty()) {
            printer.printMessage(leaderboardMessages.getNoLeaderboardError());

            return this;
        }

        TablePrinter<MainLeaderboardEntry> tablePrinter = new TablePrinter<>(printer);
        tablePrinter.print(
                optionalLeaderboard.get(),
                List.of(
                        new Column<>(leaderboardMessages.getHeaderName(),
                                entry -> AnsiSafeFormatter.isolate(entry.playerName())),
                        new Column<>(leaderboardMessages.getHeaderTurns(),
                                entry -> String.valueOf(entry.numberOfTurns())),
                        new Column<>(leaderboardMessages.getHeaderTime(),
                                entry -> ClockDisplayFormatter.format(entry.gameDuration()))
                )
        );

        waitForConfirmation();

        return this;
    }

    private Menu printTimeLeaderboard() {
        Optional<List<TimeLeaderboardEntry>> optionalLeaderboard = leaderboardService.getTimeLeaderboard();

        if (optionalLeaderboard.isEmpty()) {
            printer.printMessage(leaderboardMessages.getNoLeaderboardError());

            return this;
        }

        TablePrinter<TimeLeaderboardEntry> tablePrinter = new TablePrinter<>(printer);
        tablePrinter.print(
                optionalLeaderboard.get(),
                List.of(
                        new Column<>(leaderboardMessages.getHeaderName(),
                                entry -> AnsiSafeFormatter.isolate(entry.playerName())),
                        new Column<>(leaderboardMessages.getHeaderTime(),
                                entry -> ClockDisplayFormatter.format(entry.gameDuration()))
                )
        );

        waitForConfirmation();

        return this;
    }

    private Menu printTurnsLeaderboard() {
        Optional<List<TurnsLeaderboardEntry>> optionalLeaderboard = leaderboardService.getTurnsLeaderboard();

        if (optionalLeaderboard.isEmpty()) {
            printer.printMessage(leaderboardMessages.getNoLeaderboardError());

            return this;
        }

        TablePrinter<TurnsLeaderboardEntry> tablePrinter = new TablePrinter<>(printer);
        tablePrinter.print(
                optionalLeaderboard.get(),
                List.of(
                        new Column<>(leaderboardMessages.getHeaderName(),
                                entry -> AnsiSafeFormatter.isolate(entry.playerName())),
                        new Column<>(leaderboardMessages.getHeaderTurns(),
                                entry -> String.valueOf(entry.numberOfTurns()))
                )
        );

        waitForConfirmation();

        return this;
    }

    private Menu handleWinPercentageSelection() {
        final int minGamesForPercentage = 10;

        int maxGamesPlayedByPlayer = gamesService.getMaxGamesPlayedByPlayer();

        if (maxGamesPlayedByPlayer >= minGamesForPercentage) {
            return new WinPercentageLeaderboardMenu(appContext, maxGamesPlayedByPlayer);
        } else {
            printer.printMessage(leaderboardMessages.getNotEnoughGamesPlayedError());

            return this;
        }
    }

    private Menu printWinsLeaderboard() {
        Optional<List<WinsLeaderboardEntry>> optionalLeaderboard =
                leaderboardService.getWinsLeaderboard();

        if (optionalLeaderboard.isEmpty()) {
            printer.printMessage(leaderboardMessages.getNoLeaderboardError());

            return this;
        }

        TablePrinter<WinsLeaderboardEntry> tablePrinter = new TablePrinter<>(printer);
        tablePrinter.print(
                optionalLeaderboard.get(),
                List.of(
                        new Column<>(leaderboardMessages.getHeaderName(),
                                entry -> AnsiSafeFormatter.isolate(entry.playerName())),
                        new Column<>(leaderboardMessages.getHeaderWins(),
                                entry -> String.valueOf(entry.numberOfWins()))
                )
        );

        waitForConfirmation();

        return this;
    }

    private Menu quit() {
        return new MainMenu(appContext);
    }

    private void waitForConfirmation() {
        printer.printMessage(interactionMessages.getPressEnter());

        showMenuOnNextLoop = true;

        parser.parseUserInput();
    }
}
