package dev.bibikvlad.mastermind.menu.main.profile.selection;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.profile.ProfileMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.List;
import java.util.Optional;

public class PlayerSelectionMenu extends Menu {
    private final Printer printer;
    private final Parser parser;
    private final PlayerService playerService;

    public PlayerSelectionMenu(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.playerService = appContext.services().getPlayerService();
    }

    @Override
    public Menu run() {
        List<Player> playerList = getAllPlayers();

        displayPlayers(playerList);

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

        return selection
                .map(input -> playerSelection(playerList, input))
                .orElseGet(() -> new ProfileMenu(appContext));
    }

    private Menu playerSelection(List<Player> playerList, int userInputNumber) {
        Player player = selectPlayer(playerList, userInputNumber);

        if (player == null) {
            printer.printMessage("Invalid input. Please enter a number corresponding to the player index.");

            return this;
        } else {
            playerService.updateLastSelectedPlayer(player.getId());

            printer.printMessage("Player " + player.getPlayerName() + " has been selected.");
        }

        return new ProfileMenu(updateAppContext(player));
    }

    private List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    private void displayPlayers(List<Player> playerList) {
        for (int i = 0; i < playerList.size(); i++) {
            printer.printMessage((i + 1) + ": " + playerList.get(i).getPlayerName());
        }

        printer.printMessage("0. Back to the profile menu");
    }

    private Player selectPlayer(List<Player> playerList, int playerIndex) {
        Player player;

        try {
            player = playerList.get(playerIndex - 1);
        } catch (IndexOutOfBoundsException exception) {
            return null;
        }

        return player;
    }

    private AppContext updateAppContext(Player player) {
        return new AppContext(new LocalizationContext(player.getPlayerConfig().locale()),
                this.appContext.services(), this.printer, this.parser, player);
    }
}
