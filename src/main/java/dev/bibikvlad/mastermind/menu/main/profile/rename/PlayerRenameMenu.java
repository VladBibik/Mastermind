package dev.bibikvlad.mastermind.menu.main.profile.rename;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.exceptions.PlayerNotFoundException;
import dev.bibikvlad.mastermind.input.interpreter.GlobalMenuCommands;
import dev.bibikvlad.mastermind.input.interpreter.PlayerCreationInput;
import dev.bibikvlad.mastermind.input.interpreter.PlayerCreationInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.PlayerNameValidator;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.create.NewPlayerCreationMenuMessages;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.profile.ProfileMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class PlayerRenameMenu extends Menu {
    private final Printer printer;
    private final Parser parser;
    private final PlayerService playerService;
    private final Player currentPlayer;
    private final InteractionMessages interactionMessages;
    //TODO: Temp solution. Take exit handling logic from the creation properties file, and move it to the common one!
    private final NewPlayerCreationMenuMessages creationMessages;
    private final PlayerNameValidator validator;

    public PlayerRenameMenu(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.playerService = appContext.services().getPlayerService();
        this.currentPlayer = appContext.currentPlayer();
        this.interactionMessages = appContext.localizationContext().getMessages(MessageType.INTERACTION);
        this.creationMessages = appContext.localizationContext().getMessages(MessageType.CREATE);
        this.validator = new PlayerNameValidator(printer, creationMessages);
    }

    @Override
    public Menu run() {
        printer.printMessage("Please enter a new Player's name for: " + currentPlayer.getPlayerName());
        printer.printMessage("To go back to the previous menu enter 'exit' o 'close'");

        //TODO: Rename PlayerCreationInputInterpreter since it is used not only by player creation menus!
        PlayerCreationInput selection = PlayerCreationInputInterpreter.readSelection(parser);

        if (!handleExit(selection)) {
            return new ProfileMenu(appContext);
        }

        String playerName = selection.userInput();

        try {
            playerService.updatePlayerName(currentPlayer.getId(), playerName);

            printer.printMessage("Rename operation has been successful. New name is " + currentPlayer.getPlayerName());
            confirmToContinue();

            return new ProfileMenu(createNewAppContext(playerName));
        } catch (PlayerAlreadyExistException exception) {
            printer.printMessage("Player with name " + playerName + " already exists\n");
        }

        return this;
    }

    //TODO: The same method is used in 3 different classes. Refactor it to remove duplication!
    private boolean handleExit(PlayerCreationInput selection) {
        if (selection.isExit()) {
            printer.printMessage(creationMessages.getReservedCommandConfirmation(selection.userInput()));

            String confirmation = parser.parseUserInput();
            confirmation = confirmation.trim().toLowerCase();

            return GlobalMenuCommands.YES.contains(confirmation);
        }

        return true;
    }

    private AppContext createNewAppContext(String playerName) {
        Player player = playerService.loadLastSelectedPlayer().orElseThrow(
                () -> new PlayerNotFoundException("Could not load player with name " + playerName));

        return new AppContext(appContext.localizationContext(), appContext.services(), printer, parser, player);
    }

    private void confirmToContinue() {
        printer.printMessage(interactionMessages.getPressEnter());

        parser.parseUserInput();
    }
}
