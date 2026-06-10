package dev.bibikvlad.mastermind.localization.messages.menu.main.profile.create;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ConsoleNewPlayerCreationMessages implements NewPlayerCreationMenuMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleNewPlayerCreationMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getNewPlayerTitle() {
        return resourceBundle.getString("new_player_title");
    }

    @Override
    public String getPlayerCreatedSuccess(String playerName) {
        return MessageFormat.format(resourceBundle.getString("player_created_success"), playerName);
    }

    @Override
    public String getPlayerAlreadyExistsError(String playerName) {
        return MessageFormat.format(resourceBundle.getString("player_already_exists_error"), playerName);
    }

    @Override
    public String getPlayerNameEmptyError(String playerName) {
        return MessageFormat.format(resourceBundle.getString("player_name_empty_error"), playerName);
    }

    @Override
    public String getPlayerNameLengthError(String playerName) {
        return MessageFormat.format(resourceBundle.getString("player_name_length_error"), playerName);
    }

    @Override
    public String getReservedCommandConfirmation(String command) {
        return MessageFormat.format(resourceBundle.getString("reserved_command_confirmation"), command);
    }
}
