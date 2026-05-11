package dev.bibikvlad.mastermind.localization.messages.menu.main.profile.selection;

import java.util.ResourceBundle;

public class ConsoleSelectionMenuMessages implements PlayerSelectionMenuMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleSelectionMenuMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getBackMenuOption() {
        return resourceBundle.getString("back_menu_option");
    }

    @Override
    public String getPlayerSelected(String playerName) {
        return String.format(resourceBundle.getString("player_selected"), playerName);
    }
}
