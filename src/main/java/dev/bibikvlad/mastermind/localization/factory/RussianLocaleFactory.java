package dev.bibikvlad.mastermind.localization.factory;

import dev.bibikvlad.mastermind.localization.messages.GameMessagesLocale;
import dev.bibikvlad.mastermind.localization.messages.russian.RussianGameLocale;

public class RussianLocaleFactory extends LocaleAbstractFactory {

    public RussianLocaleFactory() {
        this.gameMessagesLocale = new RussianGameLocale();
    }

    @Override
    public GameMessagesLocale getGameMessagesLocale() {
        return gameMessagesLocale;
    }
}
