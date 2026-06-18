package dev.bibikvlad.mastermind.menu.main.profile.rename;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.profile.ProfileMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class PlayerNameChanger extends Menu {
    private final Printer printer;
    private final Parser parser;
    private final PlayerService playerService;
    private final Player currentPlayer;

    public PlayerNameChanger(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.playerService = appContext.services().getPlayerService();
        this.currentPlayer = appContext.currentPlayer();
    }

    @Override
    public Menu run() {
        printer.printMessage("Please enter a new Player's name for: " + currentPlayer.getPlayerName());
        printer.printMessage("To go back to the previous menu enter 'exit' o 'close'");

        String userInput = appContext.parser().parseUserInput();

        if (StringEmptyValidator.isNullOrEmpty(userInput)) {
            printer.printMessage("Player's name cannot be empty");

            return this;
        }

        if (userInput.equalsIgnoreCase("exit") || userInput.equalsIgnoreCase("close")) {
            return new ProfileMenu(appContext);
        }

        try {
            playerService.updatePlayerName(currentPlayer.getId(), userInput);

            return new ProfileMenu(appContext);
        } catch (PlayerAlreadyExistException exception) {
            printer.printMessage("Player with name " + userInput + " already exists\n");
        }

        return this;
    }
}
