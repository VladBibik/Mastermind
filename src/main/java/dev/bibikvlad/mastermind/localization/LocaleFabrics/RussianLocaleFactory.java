package dev.bibikvlad.mastermind.localization.LocaleFabrics;

import dev.bibikvlad.mastermind.localization.GameMessages.GameMessagesLocale;
import dev.bibikvlad.mastermind.localization.GameMessages.RussianGameLocale;

public class RussianLocaleFactory extends LocaleAbstractFactory {

    public RussianLocaleFactory() {
        this.gameMessagesLocale = new RussianGameLocale();
    }

    @Override
    public GameMessagesLocale getGameMessagesLocale() {
        return gameMessagesLocale;
    }
}
