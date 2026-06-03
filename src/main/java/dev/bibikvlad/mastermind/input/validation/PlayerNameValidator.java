package dev.bibikvlad.mastermind.input.validation;

import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.create.NewPlayerCreationMenuMessages;
import dev.bibikvlad.mastermind.menu.main.profile.create.PlayerNameValidationResult;

public class PlayerNameValidator {
    private static final int MAX_NAME_LENGTH = 100;

    private final NewPlayerCreationMenuMessages messages;

    public PlayerNameValidator(NewPlayerCreationMenuMessages messages) {
        this.messages = messages;
    }

    public PlayerNameValidationResult validate(String playerName) {

        if (StringEmptyValidator.isNullOrEmpty(playerName)) {
            return new PlayerNameValidationResult(false, messages.getPlayerNameEmptyError(playerName));
        }

        if (playerName.length() > MAX_NAME_LENGTH) {
            return new PlayerNameValidationResult(false, messages.getPlayerNameLengthError(playerName));
        }

        return new PlayerNameValidationResult(true, null);
    }
}
