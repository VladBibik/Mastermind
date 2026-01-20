package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.app.bootstrap.AppContext;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class NewPlayerCreation extends Menu {
    private final PlayerService playerService;
    private final LocaleType localeType;
    private final Menu returnMenu;

    public NewPlayerCreation(AppContext appContext, LocaleType localeType, Menu returnMenu) {
        super(appContext);

        this.playerService = appContext.services().getPlayerService();
        this.localeType = localeType;
        this.returnMenu = returnMenu;
    }

    @Override
    public Menu run() {
        System.out.println("Please enter nickname of a new player");
        System.out.println("To cancel type: 'close' or 'exit'");

        String newPlayerName = appContext.parser().parseUserInput();

        if (StringEmptyValidator.isNullOrEmpty(newPlayerName)) {
            System.out.println("Player name cannot be empty");

            return this;
        }

        if (newPlayerName.equalsIgnoreCase("exit") ||
                newPlayerName.equalsIgnoreCase("close")) {
            return returnMenu;
        }

        if (newPlayerName.length() > 100) {
            System.out.println("Player name cannot be longer than 100 characters");

            return this;
        }

        try {
            Player createdPlayer = playerService.savePlayerWithProvidedLocale(newPlayerName, localeType);
            appContext.setCurrentPlayer(createdPlayer);

            System.out.println("Player with name " + newPlayerName + " has been created.\n");

            return returnMenu;
        } catch (PlayerAlreadyExistException exception) {
            System.out.println("Player with name " + newPlayerName + " already exists.\n");
        }

        return this;
    }
}
