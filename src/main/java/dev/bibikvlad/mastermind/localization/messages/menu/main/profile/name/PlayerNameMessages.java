package dev.bibikvlad.mastermind.localization.messages.menu.main.profile.name;

import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;

public interface PlayerNameMessages extends LocalizedMessages {
    String getPlayerAlreadyExistsError(String playerName);

    String getNameEmptyError();

    String getPlayerNameLengthError();

    String getReservedCommandConfirmation(String playerName);
}
