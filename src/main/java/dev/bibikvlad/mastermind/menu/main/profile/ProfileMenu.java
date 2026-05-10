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

    private boolean showMenuOnNextLoop = true;

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
        printer.printMessage(profileMenuMessages.getMenuOptions());
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
                printer.printMessage(interactionMessages.getInvalidInputMessage());

                return this;
            }
        }
    }

    private Menu switchPlayer() {
        if (checkIfEnoughPlayersExist()) {
            return new PlayerSelectionMenu(appContext);
        } else {
            return this;
        }
    }

    private Menu openNewPlayerMenu() {
        return new NewPlayerCreation(appContext);
    }

    private Menu renamePlayer() {
        return new PlayerNameChanger(appContext);
    }

    private Menu deletePlayer() {
        if (checkIfEnoughPlayersExist()) {
            return new DeletePlayerMenu(appContext);
        } else {
            return this;
        }
    }

    private Menu quit() {
        return new MainMenu(appContext);
    }

    private boolean checkIfEnoughPlayersExist() {
        if (!playerService.isMultiplePlayersRegistered()) {
            printer.printMessage(profileMenuMessages.getNotEnoughPlayersError());

            return false;
        } else {
            return true;
        }
    }
}
