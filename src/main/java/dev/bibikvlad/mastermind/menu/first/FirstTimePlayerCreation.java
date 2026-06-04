package dev.bibikvlad.mastermind.menu.first;

import dev.bibikvlad.mastermind.app.bootstrap.ServiceContainer;
import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.PlayerCreationInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.input.validation.PlayerNameValidator;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.create.NewPlayerCreationMenuMessages;
import dev.bibikvlad.mastermind.menu.main.profile.create.PlayerNameValidationResult;
import dev.bibikvlad.mastermind.model.player.Player;

import java.util.Optional;

public class FirstTimePlayerCreation {
    private final Parser parser;
    private final Printer printer;
    private final LocaleType localeType;
    private final LocalizationContext localizationContext;
    private final ServiceContainer serviceContainer;
    private final NewPlayerCreationMenuMessages creationMessages;

    public FirstTimePlayerCreation(Parser parser, Printer printer, LocaleType localeType,
                                   ServiceContainer serviceContainer) {
        this.parser = parser;
        this.printer = printer;
        this.localeType = localeType;
        this.localizationContext = new LocalizationContext(localeType);
        this.serviceContainer = serviceContainer;
        this.creationMessages = localizationContext.getMessages(MessageType.CREATE);
    }

    public AppContext createPlayerAndGetContext() {
        printer.printMessage(creationMessages.getNewPlayerTitle());

        while (true) {
            Optional<String> optionalString = PlayerCreationInputInterpreter.readSelection(parser);

            if (optionalString.isEmpty()) {
                return null;
            }

            String selection = optionalString.get();

            if (!isPlayerNameValid(selection)) {
                continue;
            }

            Player createdPlayer = serviceContainer.getPlayerService()
                    .savePlayerWithProvidedLocale(selection, localeType);

            printer.printMessage(creationMessages.getPlayerCreatedSuccess(selection));

            return new AppContext(localizationContext, serviceContainer, printer, parser,
                    createdPlayer);
        }
    }

    private boolean isPlayerNameValid(String newPlayerName) {
        PlayerNameValidator validator = new PlayerNameValidator(creationMessages);
        PlayerNameValidationResult result = validator.validate(newPlayerName);

        if (!result.valid()) {
            printer.printMessage(result.message());
        }

        return result.valid();
    }
}
