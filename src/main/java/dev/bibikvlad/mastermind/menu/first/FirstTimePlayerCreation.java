package dev.bibikvlad.mastermind.menu.first;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.PlayerNameValidator;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.create.NewPlayerCreationMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.name.PlayerNameMessages;
import dev.bibikvlad.mastermind.menu.main.profile.PlayerNameReader;
import dev.bibikvlad.mastermind.model.player.Player;

import java.util.Optional;

public class FirstTimePlayerCreation {
    private final Parser parser;
    private final Printer printer;
    private final LocaleType localeType;
    private final LocalizationContext localizationContext;
    private final ServiceContainer serviceContainer;
    private final NewPlayerCreationMenuMessages creationMessages;
    private final PlayerNameMessages nameMessages;
    private final InteractionMessages interactionMessages;
    private final PlayerNameValidator playerNameValidator;

    public FirstTimePlayerCreation(Parser parser, Printer printer, LocaleType localeType,
                                   ServiceContainer serviceContainer) {
        this.parser = parser;
        this.printer = printer;
        this.localeType = localeType;
        this.localizationContext = new LocalizationContext(localeType);
        this.serviceContainer = serviceContainer;
        this.creationMessages = localizationContext.getMessages(MessageType.CREATE);
        this.nameMessages = localizationContext.getMessages(MessageType.PLAYER_NAME);
        this.interactionMessages = localizationContext.getMessages(MessageType.INTERACTION);
        this.playerNameValidator = new PlayerNameValidator(printer, nameMessages);
    }

    public AppContext createPlayerAndGetContext() {
        printer.printMessage(creationMessages.getNewPlayerTitle());

        while (true) {
            PlayerNameReader playerNameReader = new PlayerNameReader(parser, printer, nameMessages);

            Optional<String> optionalPlayerName = playerNameReader.readPlayerName();

            if (optionalPlayerName.isEmpty()) {
                return null;
            }

            String playerName = optionalPlayerName.get();

            if (!playerNameValidator.validateAndPrintErrors(playerName)) {
                continue;
            }

            return savePlayerAndBuildContext(playerName);
        }
    }

    private AppContext savePlayerAndBuildContext(String playerName) {
        Player createdPlayer = serviceContainer.getPlayerService()
                .createPlayer(playerName, localeType);

        printer.printMessage(creationMessages.getPlayerCreatedSuccess(playerName));

        confirmToContinue();

        return new AppContext(localizationContext, serviceContainer, printer, parser,
                createdPlayer);
    }

    private void confirmToContinue() {
        printer.printMessage(interactionMessages.getPressEnter());

        parser.parseUserInput();
    }
}
