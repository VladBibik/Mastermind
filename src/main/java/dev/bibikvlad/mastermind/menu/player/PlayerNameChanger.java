package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class PlayerNameChanger extends Menu {
    private final PlayerService playerService;
    private final Player currentPlayer;

    public PlayerNameChanger(LocalizationContext localizationContext, ServiceContainer serviceContainer,
                             MastermindUserInputParser parser) {
        super(localizationContext, serviceContainer, parser);

        this.playerService = serviceContainer.getPlayerService();
        this.currentPlayer = playerService.loadLastSelectedPlayer().orElseThrow(
                () -> new IllegalStateException("No last selected player found!"));
    }

    @Override
    public Menu run() {
        System.out.println("Please enter a new Player's name for: " + currentPlayer.getPlayerName());
        System.out.println();
        System.out.println("To go back to the previous menu enter 'exit' o 'close'");

        String userInput = parser.parseUserInput();

        if (StringEmptyValidator.isNullOrEmpty(userInput)) {
            System.out.println("Player's name cannot be empty");

            return this;
        }

        if (userInput.equalsIgnoreCase("exit") || userInput.equalsIgnoreCase("close")) {
            return new ProfileMenu(localizationContext, serviceContainer, parser);
        }

        try {
            playerService.updatePlayerName(currentPlayer.getId(), userInput);

            return new ProfileMenu(localizationContext, serviceContainer, parser);
        } catch (PlayerAlreadyExistException exception) {
            System.out.println("Player with name " + userInput + " already exists\n");
        }

        return this;
    }
}
