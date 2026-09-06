package dev.bibikvlad.mastermind.input.validation;

import dev.bibikvlad.mastermind.app.printer.ConsolePrinter;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.name.ConsolePlayerNameMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.name.PlayerNameMessages;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertFalse;

class PlayerNameValidatorTest {
    private final PlayerNameMessages messages = new ConsolePlayerNameMessages(
            ResourceBundle.getBundle("i18n.menu.main.profile.name.player_name"));
    private final Printer printer = new ConsolePrinter();
    private final PlayerNameValidator playerNameValidator = new PlayerNameValidator(printer, messages);

    @Test
    @DisplayName("Name with length more than 100 returns false")
    void nameWithLengthMoreThan100ReturnsFalse() {
        StringBuilder playerName = new StringBuilder("12345678910");

        playerName.append(String.valueOf(playerName).repeat(11));

        assertFalse(playerNameValidator.validateAndPrintErrors(playerName.toString()));
    }
}
