package dev.bibikvlad.mastermind.input.validation;

import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.create.NewPlayerCreationMenuMessages;

public class PlayerNameValidator {
    private static final int MAX_NAME_LENGTH = 100;

    private final Printer printer;
    private final NewPlayerCreationMenuMessages messages;

    public PlayerNameValidator(Printer printer, NewPlayerCreationMenuMessages messages) {
        this.printer = printer;
        this.messages = messages;
    }

    public boolean validate(String playerName) {
        if (StringEmptyValidator.isNullOrEmpty(playerName)) {
            printer.printMessage(messages.getPlayerNameEmptyError(playerName));

            return false;
        }

        if (playerName.length() > MAX_NAME_LENGTH) {
            printer.printMessage(messages.getPlayerNameLengthError(playerName));

            return false;
        }

        return true;
    }
}
