package dev.bibikvlad.mastermind.localization.messages.menu.main.profile.name;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ConsolePlayerNameMessages implements PlayerNameMessages {
    private final ResourceBundle resourceBundle;

    public ConsolePlayerNameMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getAlreadyExistsError(String playerName) {
        return MessageFormat.format(resourceBundle.getString("player_already_exists_error"), playerName);
    }

    @Override
    public String getNameEmptyError() {
        return resourceBundle.getString("player_name_empty_error");
    }

    @Override
    public String getPlayerNameLengthError() {
        return resourceBundle.getString("player_name_length_error");
    }

    @Override
    public String getReservedCommandConfirmation(String playerName) {
        return MessageFormat.format(resourceBundle.getString("reserved_command_confirmation"), playerName);
    }
}
