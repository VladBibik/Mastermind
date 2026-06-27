package dev.bibikvlad.mastermind.localization.messages.menu.main.profile.rename;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ConsolePlayerRenameMessages implements PlayerRenameMenuMessages{
    private final ResourceBundle resourceBundle;

    public ConsolePlayerRenameMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getPlayerNameRenameTitle(String playerName) {
        return MessageFormat.format(resourceBundle.getString("player_name_rename_title"), playerName);
    }

    @Override
    public String getPlayerNameRenameSuccess(String playerName) {
        return resourceBundle.getString("player_name_rename_success");
    }
}
