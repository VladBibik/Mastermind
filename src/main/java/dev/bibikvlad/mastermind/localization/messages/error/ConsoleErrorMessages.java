package dev.bibikvlad.mastermind.localization.messages.error;

import java.util.ResourceBundle;

public class ConsoleErrorMessages implements ErrorMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleErrorMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getInvalidInputMessage() {
        return resourceBundle.getString("invalid_input");
    }
}
