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
    @DisplayName("Name longer than 100 characters returns false")
    void nameLongerThan100CharactersReturnsFalse() {
        String playerName = "a".repeat(101);

        assertFalse(playerNameValidator.validateAndPrintErrors(playerName));
    }

    @Test
    @DisplayName("Empty string is considered empty")
    void emptyStringIsConsideredEmpty() {
        assertFalse(playerNameValidator.validateAndPrintErrors(""));
    }

    @Test
    @DisplayName("Whitespace-only string is considered empty")
    void whitespaceOnlyStringIsConsideredEmpty() {
        assertFalse(playerNameValidator.validateAndPrintErrors("   \t\n"));
    }

    @Test
    @DisplayName("Null name returns false")
    void nullNameReturnsFalse() {
        assertFalse(playerNameValidator.validateAndPrintErrors(null));
    }
}
