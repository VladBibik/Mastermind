package dev.bibikvlad.mastermind.localization.messages.menu.main.profile.selection;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ConsolePlayerSelectionMenuMessages implements PlayerSelectionMenuMessages {
    private final ResourceBundle resourceBundle;

    public ConsolePlayerSelectionMenuMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getBackMenuOption() {
        return resourceBundle.getString("back_menu_option");
    }

    @Override
    public String getPlayerSelected(String playerName) {
        return MessageFormat.format(resourceBundle.getString("player_selected"), playerName);
    }
}
