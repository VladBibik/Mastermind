package dev.bibikvlad.mastermind.localization.messages.menu.main.profile.name;

public interface PlayerNameMessages {
    String getAlreadyExistsError(String playerName);

    String getNameEmptyError();

    String getPlayerNameLengthError();

    String getReservedCommandConfirmation(String playerName);
}
