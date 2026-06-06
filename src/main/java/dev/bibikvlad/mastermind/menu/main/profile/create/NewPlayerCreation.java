package dev.bibikvlad.mastermind.menu.main.profile.create;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.PlayerNameValidator;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.create.NewPlayerCreationMenuMessages;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.profile.ProfileMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class NewPlayerCreation extends Menu {
    private final Printer printer;
    private final Parser parser;
    private final PlayerService playerService;
    private final NewPlayerCreationMenuMessages creationMessages;

    public NewPlayerCreation(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.playerService = appContext.services().getPlayerService();
        this.creationMessages = appContext.localizationContext().getMessages(MessageType.CREATE);
    }

    @Override
    public Menu run() {
        printer.printMessage(creationMessages.getNewPlayerTitle());

        String newPlayerName = parser.parseUserInput();

        if (newPlayerName.equalsIgnoreCase("exit") ||
                newPlayerName.equalsIgnoreCase("close")) {
            return new ProfileMenu(appContext);
        }

        if (!isPlayerNameValid(newPlayerName)) {
            return this;
        }

        try {
            LocaleType localeType = appContext.currentPlayer().getPlayerConfig().locale();
            Player createdPlayer = playerService.savePlayerWithProvidedLocale(newPlayerName, localeType);
            AppContext appContext = new AppContext(this.appContext.localizationContext(), this.appContext.services(),
                    this.appContext.printer(), this.appContext.parser(), createdPlayer);

            printer.printMessage(creationMessages.getPlayerCreatedSuccess(newPlayerName));

            return new ProfileMenu(appContext);
        } catch (PlayerAlreadyExistException exception) {
            printer.printMessage(creationMessages.getPlayerAlreadyExistsError(newPlayerName));
        }

        return this;
    }

    private boolean isPlayerNameValid(String newPlayerName) {
        PlayerNameValidator validator = new PlayerNameValidator(printer, creationMessages);

        return validator.validate(newPlayerName);
    }
}
