package dev.bibikvlad.mastermind.localization.messages.error;

import java.util.ResourceBundle;

public class ConsoleInteractionMessages implements InteractionMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleInteractionMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getInvalidInputMessage() {
        return resourceBundle.getString("error.invalid_input");
    }
}
