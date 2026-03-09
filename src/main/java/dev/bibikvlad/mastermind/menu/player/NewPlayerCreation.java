package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.persistence.player.model.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class NewPlayerCreation extends Menu {
    private final PlayerService playerService;

    public NewPlayerCreation(AppContext appContext) {
        super(appContext);

        this.playerService = appContext.services().getPlayerService();
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
            return new ProfileMenu(appContext);
        }

        if (newPlayerName.length() > 100) {
            System.out.println("Player name cannot be longer than 100 characters");

            return this;
        }

        try {
            LocaleType localeType = appContext.currentPlayer().getPlayerConfig().locale();
            Player createdPlayer = playerService.savePlayerWithProvidedLocale(newPlayerName, localeType);
            AppContext appContext = new AppContext(this.appContext.localizationContext(), this.appContext.services(),
                    this.appContext.printer(), this.appContext.parser(), createdPlayer);

            System.out.println("Player with name " + newPlayerName + " has been created.\n");

            return new ProfileMenu(appContext);
        } catch (PlayerAlreadyExistException exception) {
            System.out.println("Player with name " + newPlayerName + " already exists.\n");
        }

        return this;
    }
}
