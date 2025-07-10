package dev.bibikvlad.mastermind.localization.factory;

import dev.bibikvlad.mastermind.localization.messages.GameMessagesLocale;

public abstract class LocaleAbstractFactory {
    protected GameMessagesLocale gameMessagesLocale;

    public abstract GameMessagesLocale getGameMessagesLocale();
}
