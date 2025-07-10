package dev.bibikvlad.mastermind.localization.factory;

import dev.bibikvlad.mastermind.localization.GameMessages.EnglishGameLocale;
import dev.bibikvlad.mastermind.localization.GameMessages.GameMessagesLocale;

public class EnglishLocaleFactory extends LocaleAbstractFactory {

    public EnglishLocaleFactory() {
        this.gameMessagesLocale = new EnglishGameLocale();
    }

    @Override
    public GameMessagesLocale getGameMessagesLocale() {
        return gameMessagesLocale;
    }
}
