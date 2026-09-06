package dev.bibikvlad.mastermind.input.validation;

import dev.bibikvlad.mastermind.app.printer.ConsolePrinter;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.name.ConsolePlayerNameMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.name.PlayerNameMessages;

import java.util.ResourceBundle;

public class PlayerNameValidatorTest {
    private final PlayerNameMessages messages = new ConsolePlayerNameMessages(
            ResourceBundle.getBundle("i18n.menu.main.profile.name.player_name"));
    private final Printer printer = new ConsolePrinter();
    private final PlayerNameValidator playerNameValidator = new PlayerNameValidator(printer, messages);
}
