package dev.bibikvlad.mastermind.chat.LocaleFabrics;

import dev.bibikvlad.mastermind.chat.GameMessages.EnglishGameLocale;
import dev.bibikvlad.mastermind.chat.GameMessages.GameMessagesLocale;

public class EnglishLocaleFactory extends LocaleSwitchAbstractFactory {

    public EnglishLocaleFactory() {
        this.gameMessagesLocale = new EnglishGameLocale();
    }

    @Override
    public GameMessagesLocale getGameMessagesLocale() {
        return gameMessagesLocale;
    }
}
