package dev.bibikvlad.mastermind.input.validation;

import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.name.PlayerNameMessages;

public class PlayerNameValidator {
    private static final int MAX_NAME_LENGTH = 100;

    private final Printer printer;
    private final PlayerNameMessages messages;

    public PlayerNameValidator(Printer printer, PlayerNameMessages messages) {
        this.printer = printer;
        this.messages = messages;
    }

    public boolean validateAndPrintErrors(String playerName) {
        if (StringEmptyValidator.isNullOrEmpty(playerName)) {
            printer.printMessage(messages.getPlayerNameEmptyError());

            return false;
        }

        if (playerName.length() > MAX_NAME_LENGTH) {
            printer.printMessage(messages.getPlayerNameLengthError());

            return false;
        }

        return true;
    }
}
