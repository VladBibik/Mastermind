package dev.bibikvlad.mastermind.menu.main.profile.create;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.exceptions.PlayerAlreadyExistException;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.PlayerNameValidator;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.create.NewPlayerCreationMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.name.PlayerNameMessages;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.profile.PlayerNameReader;
import dev.bibikvlad.mastermind.menu.main.profile.ProfileMenu;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

import java.util.Optional;

public class CreatePlayerMenu extends Menu {
    private final Printer printer;
    private final Parser parser;
    private final PlayerService playerService;
    private final NewPlayerCreationMenuMessages creationMessages;
    private final PlayerNameMessages nameMessages;
    private final InteractionMessages interactionMessages;
    private final PlayerNameValidator playerNameValidator;

    public CreatePlayerMenu(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.parser = appContext.parser();
        this.playerService = appContext.services().getPlayerService();
        this.creationMessages = appContext.localizationContext().getMessages(MessageType.CREATE);
        this.nameMessages = appContext.localizationContext().getMessages(MessageType.PLAYER_NAME);
        this.interactionMessages = appContext.localizationContext().getMessages(MessageType.INTERACTION);
        this.playerNameValidator = new PlayerNameValidator(printer, nameMessages);
    }

    @Override
    public Menu run() {
        printer.printMessage(creationMessages.getNewPlayerTitle());

        PlayerNameReader playerNameReader = new PlayerNameReader(parser, printer, nameMessages);

        Optional<String> optionalPlayerName = playerNameReader.readPlayerName();

        if (optionalPlayerName.isEmpty()) {
            return new ProfileMenu(appContext);
        }

        String playerName = optionalPlayerName.get();

        if (!playerNameValidator.validateAndPrintErrors(playerName)) {
            return this;
        }

        try {
            return new ProfileMenu(savePlayerAndBuildContext(playerName));
        } catch (PlayerAlreadyExistException _) {
            printer.printMessage(nameMessages.getPlayerAlreadyExistsError(playerName));
        }

        return this;
    }

    private AppContext savePlayerAndBuildContext(String playerName) {
        Player createdPlayer = playerService.createPlayer(playerName,
                appContext.currentPlayer().getPlayerConfig().locale());

        printer.printMessage(creationMessages.getPlayerCreatedSuccess(playerName));

        confirmToContinue();

        return new AppContext(this.appContext.localizationContext(), this.appContext.services(),
                this.appContext.printer(), this.appContext.parser(), createdPlayer);
    }

    private void confirmToContinue() {
        printer.printMessage(interactionMessages.getPressEnter());

        parser.parseUserInput();
    }
}
