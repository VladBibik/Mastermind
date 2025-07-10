package dev.bibikvlad.mastermind.localization.manager;

import dev.bibikvlad.mastermind.localization.configurations.LocaleType;
import dev.bibikvlad.mastermind.localization.factory.EnglishLocaleFactory;
import dev.bibikvlad.mastermind.localization.factory.LocaleAbstractFactory;
import dev.bibikvlad.mastermind.localization.factory.RussianLocaleFactory;
import dev.bibikvlad.mastermind.localization.messages.GameMessagesLocale;

public class LocaleManager {
    private final LocaleAbstractFactory localeFactory;

    public LocaleManager(LocaleType type) {
        this.localeFactory = switch (type) {
            case ENGLISH -> new EnglishLocaleFactory();
            case RUSSIAN -> new RussianLocaleFactory();
        };
    }

    public GameMessagesLocale getGameMessagesLocale() {
        return localeFactory.getGameMessagesLocale();
    }
}
