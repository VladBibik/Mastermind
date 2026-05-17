package dev.bibikvlad.mastermind.menu.main.profile.selection;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.AnsiSafeFormatter;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.exceptions.PlayerNotFoundException;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.error.ErrorMessages;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.selection.PlayerSelectionMenuMessages;
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
    private final PlayerSelectionMenuMessages selectionMenuMessages;
    private final InteractionMessages interactionMessages;
    private final ErrorMessages errorMessages;

    public PlayerSelectionMenu(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.playerService = appContext.services().getPlayerService();
        this.interactionMessages = appContext.localizationContext().getMessages(MessageType.INTERACTION);
        this.selectionMenuMessages = appContext.localizationContext().getMessages(MessageType.SELECTION);
        this.errorMessages = appContext.localizationContext().getMessages(MessageType.ERROR);
    }

    @Override
    public Menu run() {
        List<Player> playerList = getAllPlayers();

        displayMenuOptions(playerList);

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

        return selection
                .map(input -> playerSelection(playerList, input))
                .orElseGet(() -> {
                    Player lastSelectedPlayer = playerService.loadLastSelectedPlayer()
                            .orElseThrow(() -> new PlayerNotFoundException(
                                    errorMessages.getLastSelectedPlayerNotFound()));

                    return new ProfileMenu(updateAppContext(lastSelectedPlayer));
                });
    }

    private Menu playerSelection(List<Player> playerList, int userInputNumber) {
        Optional<Player> optionalPlayer = selectPlayer(playerList, userInputNumber);

        if (optionalPlayer.isEmpty()) {
            printer.printMessage(interactionMessages.getInvalidInput());

            return this;
        } else {
            Player player = optionalPlayer.get();

            playerService.updateLastSelectedPlayer(player.getId());

            printer.printMessage(selectionMenuMessages.getPlayerSelected(
                    AnsiSafeFormatter.isolate(player.getPlayerName())));

            return new ProfileMenu(updateAppContext(player));
        }
    }

    private List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    private void displayMenuOptions(List<Player> playerList) {
        for (int i = 0; i < playerList.size(); i++) {
            String playerName = AnsiSafeFormatter.isolate(playerList.get(i).getPlayerName());

            printer.printMessage((i + 1) + ": " + playerName);
        }

        printer.printMessage(selectionMenuMessages.getBackMenuOption());
    }

    private Optional<Player> selectPlayer(List<Player> playerList, int playerIndex) {
        int index = playerIndex - 1;

        if (index < 0 || index >= playerList.size()) {
            return Optional.empty();
        }

        return Optional.of(playerList.get(index));
    }

    private AppContext updateAppContext(Player player) {
        return new AppContext(new LocalizationContext(player.getPlayerConfig().locale()),
                this.appContext.services(), this.printer, this.parser, player);
    }
}
