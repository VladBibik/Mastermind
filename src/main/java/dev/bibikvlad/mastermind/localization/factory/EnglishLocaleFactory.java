package dev.bibikvlad.mastermind.localization.factory;

import dev.bibikvlad.mastermind.localization.messages.english.EnglishGameLocale;
import dev.bibikvlad.mastermind.localization.messages.GameMessagesLocale;

public class EnglishLocaleFactory extends LocaleAbstractFactory {

    public EnglishLocaleFactory() {
        this.gameMessagesLocale = new EnglishGameLocale();
    }

    @Override
    public GameMessagesLocale getGameMessagesLocale() {
        return gameMessagesLocale;
    }
}
