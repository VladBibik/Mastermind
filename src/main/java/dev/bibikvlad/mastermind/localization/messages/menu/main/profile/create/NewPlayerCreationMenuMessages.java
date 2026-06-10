package dev.bibikvlad.mastermind.localization.messages.menu.main.profile.create;

import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;

public interface NewPlayerCreationMenuMessages extends LocalizedMessages {
    String getNewPlayerTitle();

    String getPlayerCreatedSuccess(String playerName);

    String getPlayerAlreadyExistsError(String playerName);

    String getPlayerNameEmptyError(String playerName);

    String getPlayerNameLengthError(String playerName);

    String getReservedCommandConfirmation(String command);
}
