package dev.bibikvlad.mastermind.menu.main.profile;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.menu.main.MainMenu;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.profile.delete.DeletePlayerMenu;
import dev.bibikvlad.mastermind.menu.main.profile.create.NewPlayerCreation;
import dev.bibikvlad.mastermind.menu.main.profile.rename.PlayerNameChanger;
import dev.bibikvlad.mastermind.menu.main.profile.selection.PlayerSelectionMenu;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

public class ProfileMenu extends Menu {
    private final Printer printer;
    private final Parser parser;
    private final PlayerService playerService;

    public ProfileMenu(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.playerService = appContext.services().getPlayerService();
    }

    @Override
    public Menu run() {
        displayMenu();

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(appContext.parser());

        return selection
                .map(this::menuOptionSwitcher)
                .orElseGet(() -> new MainMenu(appContext));

    }

    private void displayMenu() {
        System.out.println();
        System.out.println("1. Switch");
        System.out.println("2. Create");
        System.out.println("3. Rename");
        System.out.println("4. Delete");
        System.out.println("0. Back");
    }

    private Menu menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> {
                return switchPlayer();
            }
            case 2 -> {
                return newPlayerCreation();
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
                System.out.println("Invalid input. Please enter a number corresponding to the menu option.");

                return this;
            }
        }
    }

    private Menu switchPlayer() {
        if (!playerService.isMultiplePlayersRegistered()) {
            System.out.println("Please register at least one more player first.");

            return this;
        }

        Menu playerSelectionMenu = new PlayerSelectionMenu(appContext);

        return playerSelectionMenu.run();
    }

    private Menu newPlayerCreation() {
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
