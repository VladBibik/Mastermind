package dev.bibikvlad.mastermind.chat.LocaleFabrics;

import dev.bibikvlad.mastermind.chat.GameMessages.GameMessagesLocale;

public abstract class LocaleAbstractFactory {
    protected GameMessagesLocale gameMessagesLocale;

    public abstract GameMessagesLocale getGameMessagesLocale();
}
