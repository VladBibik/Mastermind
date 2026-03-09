package dev.bibikvlad.mastermind.menu.main.stats;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.stats.StatsMessages;
import dev.bibikvlad.mastermind.menu.main.MainMenu;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.persistence.player.model.PlayerStatistics;
import dev.bibikvlad.mastermind.services.PlayerStatisticsService;

public class StatsMenu extends Menu {
    private final PlayerStatisticsService playerStatisticsService;
    private final Player currentPlayer;
    private final StatsMessages statsMessages;
    private final InteractionMessages interactionMessages;
    private final Printer printer;
    private final Parser parser;

    public StatsMenu(AppContext appContext) {
        super(appContext);

        this.playerStatisticsService = appContext.services().getPlayerStatisticsService();
        this.currentPlayer = appContext.currentPlayer();
        this.statsMessages = appContext.localizationContext().getMessages(MessageType.STATS);
        this.interactionMessages = appContext.localizationContext().getMessages(MessageType.INTERACTION);
        this.printer = appContext.printer();
        this.parser = appContext.parser();
    }

    @Override
    public Menu run() {
        printStats();

        confirmToContinue();

        return new MainMenu(appContext);
    }

    private void printStats() {
        PlayerStatistics stats = fetchPlayerStatistics(currentPlayer.getId());

        printer.printMessage(statsMessages.getDefaultStatArrangement(currentPlayer.getPlayerName(), stats));
    }

    private PlayerStatistics fetchPlayerStatistics(long playerId) {
        return playerStatisticsService.getPlayerStatistics(playerId)
                .orElseThrow(() -> new IllegalStateException("Statistics for the player with ID: "
                        + playerId + " not found"));
    }

    private void confirmToContinue() {
        printer.printMessage(interactionMessages.getPressEnterMessage());

        parser.parseUserInput();
    }
}
