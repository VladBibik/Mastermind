package dev.bibikvlad.mastermind.localization.messages.menu.main.profile.delete;

import dev.bibikvlad.mastermind.model.enums.ConsoleColor;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ConsoleDeletePlayerMessages implements DeletePlayerMenuMessages {
    private static final String RED_CONSOLE_CODE = ConsoleColor.RED.getCode();
    private static final String CONSOLE_RESET_CODE = ConsoleColor.RESET.getCode();

    private final ResourceBundle resourceBundle;

    public ConsoleDeletePlayerMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getFirstWarning(String playerName) {
        String coloredWarningTitle = RED_CONSOLE_CODE + resourceBundle.getString("first_warning_title")
                + CONSOLE_RESET_CODE;

        return coloredWarningTitle + "\n"
                + MessageFormat.format(resourceBundle.getString("first_warning"), playerName);
    }

    @Override
    public String getDeleteConfirmation(String playerName) {
        String coloredWarningTitle = RED_CONSOLE_CODE + resourceBundle.getString("delete_confirmation_title")
                + CONSOLE_RESET_CODE;

        return coloredWarningTitle + "\n"
                + MessageFormat.format(resourceBundle.getString("delete_confirmation"), playerName);
    }

    @Override
    public String getPlayerDeleted(String playerName) {
        return MessageFormat.format(resourceBundle.getString("player_deleted"), playerName);
    }

    @Override
    public String getPlayerAutoSelected(String playerName) {
        return MessageFormat.format(resourceBundle.getString("player_auto_selected"), playerName);
    }

    @Override
    public String getAutoSelectionWarning(String playerName) {
        return MessageFormat.format(resourceBundle.getString("automatic_player_selection_warning"), playerName);
    }
}
