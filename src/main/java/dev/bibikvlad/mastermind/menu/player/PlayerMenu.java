package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.MainMenu;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

public class PlayerMenu extends Menu {
    private final PlayerService playerService;
    private final Player currentPlayer;

    public PlayerMenu(LocalizationContext localizationContext, ServiceContainer serviceContainer,
                      MastermindUserInputParser parser) {
        super(localizationContext, serviceContainer, parser);

        this.playerService = serviceContainer.getPlayerService();
        this.currentPlayer = playerService.loadLastSelectedPlayer().orElseThrow(
                () -> new IllegalStateException("No last selected player found!"));
    }

    @Override
    public Menu run() {
        displayMenu();

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(parser);

        //TODO: Check if map even needed here coz we have quit method. The way it works now it either goes to the main
        //menu if user input is '4', or 'close'/'exit'
        return selection
                .map(this::menuOptionSwitcher)
                .orElseGet(() -> new MainMenu(localizationContext, serviceContainer, parser));

    }

    private void displayMenu() {
        System.out.println();
        System.out.println("1. Change player");
        System.out.println("2. Change player's name");
        System.out.println("3. Delete current player's data");
        System.out.println("4. Back to main menu");
    }

    private Menu menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> {
                return changePlayer();
            }
            case 2 -> {
                return changePlayerName();
            }
            case 3 -> {
                return deletePlayer();
            }
            case 4 -> {
                return quit();
            }
            default -> {
                System.out.println("Invalid input. Please enter a number corresponding to the menu option.");

                return this;
            }
        }
    }

    private Menu changePlayer() {
        if (!playerService.isMultiplePlayersRegistered()) {
            System.out.println("Please register at least one more player first.");

            return this;
        }

        Menu playerSelectionMenu = new PlayerSelectionMenu(localizationContext, serviceContainer, parser);

        return playerSelectionMenu.run();
    }

    private Menu newPlayerCreation() {
        return new NewPlayerCreation(localizationContext, serviceContainer, parser,
                currentPlayer.getPlayerConfig().locale(), this);
    }

    private Menu changePlayerName() {
        return new PlayerNameChanger(localizationContext, serviceContainer, parser);
    }

    private Menu deletePlayer() {
        return new DeletePlayerMenu(localizationContext, serviceContainer, parser);
    }

    private Menu quit() {
        return new MainMenu(localizationContext, serviceContainer, parser);
    }
}
