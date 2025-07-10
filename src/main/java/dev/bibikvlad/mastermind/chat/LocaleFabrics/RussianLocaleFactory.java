package dev.bibikvlad.mastermind.chat.LocaleFabrics;

import dev.bibikvlad.mastermind.chat.GameMessages.GameMessagesLocale;
import dev.bibikvlad.mastermind.chat.GameMessages.RussianGameLocale;

public class RussianLocaleFactory extends LocaleSwitchAbstractFactory {

    public RussianLocaleFactory() {
        this.gameMessagesLocale = new RussianGameLocale();
    }

    @Override
    public GameMessagesLocale getGameMessagesLocale() {
        return gameMessagesLocale;
    }
}
