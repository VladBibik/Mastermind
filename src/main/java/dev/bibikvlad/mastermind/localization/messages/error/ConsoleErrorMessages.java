package dev.bibikvlad.mastermind.localization.messages.error;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ConsoleErrorMessages implements ErrorMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleErrorMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getStatsNotFoundMessage(long playerId) {
        return MessageFormat.format(resourceBundle.getString("stats_not_found"), playerId);
    }
}
