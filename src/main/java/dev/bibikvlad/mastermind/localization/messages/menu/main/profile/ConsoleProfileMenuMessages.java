package dev.bibikvlad.mastermind.localization.messages.menu.main.profile;

import java.util.ResourceBundle;

public class ConsoleProfileMenuMessages implements ProfileMenuMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleProfileMenuMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getMenuOptions() {
        return resourceBundle.getString("menu_options");
    }

    @Override
    public String getMultiplePlayersRequired() {
        return resourceBundle.getString("multiple_players_required");
    }
}
