package dev.bibikvlad.mastermind.menu.main.profile.create;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.StringEmptyValidator;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.profile.ProfileMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class NewPlayerCreation extends Menu {
    private final Printer printer;
    private final Parser parser;
    private final PlayerService playerService;

    public NewPlayerCreation(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.playerService = appContext.services().getPlayerService();
    }

    @Override
    public Menu run() {
        printer.printMessage("Please enter nickname of a new player");
        printer.printMessage("To cancel type: 'close' or 'exit'");

        String newPlayerName = parser.parseUserInput();

        if (StringEmptyValidator.isNullOrEmpty(newPlayerName)) {
            printer.printMessage("Player name cannot be empty");

            return this;
        }

        if (newPlayerName.equalsIgnoreCase("exit") ||
                newPlayerName.equalsIgnoreCase("close")) {
            return new ProfileMenu(appContext);
        }

        if (newPlayerName.length() > 100) {
            printer.printMessage("Player name cannot be longer than 100 characters");

            return this;
        }

        try {
            LocaleType localeType = appContext.currentPlayer().getPlayerConfig().locale();
            Player createdPlayer = playerService.savePlayerWithProvidedLocale(newPlayerName, localeType);
            AppContext appContext = new AppContext(this.appContext.localizationContext(), this.appContext.services(),
                    this.appContext.printer(), this.appContext.parser(), createdPlayer);

            printer.printMessage("Player with name " + newPlayerName + " has been created.\n");

            return new ProfileMenu(appContext);
        } catch (PlayerAlreadyExistException exception) {
            printer.printMessage("Player with name " + newPlayerName + " already exists.\n");
        }

        return this;
    }
}
