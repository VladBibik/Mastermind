package dev.bibikvlad.mastermind.localization.messages.error;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ConsoleErrorMessages implements ErrorMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleErrorMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getStatsNotFound(long playerId) {
        return MessageFormat.format(resourceBundle.getString("stats.no_statistics_found"), playerId);
    }

    @Override
    public String getLastSelectedPlayerNotFound() {
        return resourceBundle.getString("last_selected_player_not_found");
    }
}
