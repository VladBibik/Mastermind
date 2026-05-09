package dev.bibikvlad.mastermind.menu.main.profile;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.ProfileMenuMessages;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.MainMenu;
import dev.bibikvlad.mastermind.menu.main.profile.create.NewPlayerCreation;
import dev.bibikvlad.mastermind.menu.main.profile.delete.DeletePlayerMenu;
import dev.bibikvlad.mastermind.menu.main.profile.rename.PlayerNameChanger;
import dev.bibikvlad.mastermind.menu.main.profile.selection.PlayerSelectionMenu;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

public class ProfileMenu extends Menu {
    private final Printer printer;
    private final Parser parser;
    private final InteractionMessages interactionMessages;
    private final ProfileMenuMessages profileMenuMessages;
    private final PlayerService playerService;

    public ProfileMenu(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.interactionMessages = appContext.localizationContext().getMessages(MessageType.INTERACTION);
        this.profileMenuMessages = appContext.localizationContext().getMessages(MessageType.PROFILE);
        this.playerService = appContext.services().getPlayerService();
    }

    @Override
    public Menu run() {
        displayMenu();

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

        return selection
                .map(this::menuOptionSwitcher)
                .orElseGet(() -> new MainMenu(appContext));

    }

    private void displayMenu() {
        printer.printMessage("""
                1. Switch
                2. Create
                3. Rename
                4. Delete
                0. Back
                """);

    }

    private Menu menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> {
                return switchPlayer();
            }
            case 2 -> {
                return openNewPlayerMenu();
            }
            case 3 -> {
                return renamePlayer();
            }
            case 4 -> {
                return deletePlayer();
            }
            case 0 -> {
                return quit();
            }
            default -> {
                printer.printMessage("Invalid input. Please enter a number corresponding to the menu option.");

                return this;
            }
        }
    }

    private Menu switchPlayer() {
        if (!playerService.isMultiplePlayersRegistered()) {
            printer.printMessage("Please register at least one more player first.");

            return this;
        }

        return new PlayerSelectionMenu(appContext);
    }

    private Menu openNewPlayerMenu() {
        return new NewPlayerCreation(appContext);
    }

    private Menu renamePlayer() {
        return new PlayerNameChanger(appContext);
    }

    private Menu deletePlayer() {
        return new DeletePlayerMenu(appContext);
    }

    private Menu quit() {
        return new MainMenu(appContext);
    }
}
