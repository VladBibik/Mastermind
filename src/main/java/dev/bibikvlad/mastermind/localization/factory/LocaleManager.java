package dev.bibikvlad.mastermind.localization.factory;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.manager.ConsoleMessageBundle;
import dev.bibikvlad.mastermind.localization.manager.MessageBundle;

import java.util.ResourceBundle;

public class LocaleManager {
    private final MessageBundle messageBundle;

    public LocaleManager(LocaleType localeType) {
        this.messageBundle = getLocalizedMessageBundle(localeType);
    }

    public MessageBundle getMessageBundle() {
        return messageBundle;
    }

    private MessageBundle getLocalizedMessageBundle(LocaleType localeType) {
        return switch (localeType) {
            case ENGLISH -> new ConsoleMessageBundle(ResourceBundle.getBundle("i18n.game_messages_en"));
            case RUSSIAN -> new ConsoleMessageBundle(ResourceBundle.getBundle("i18n.game_messages_ru"));
        };
    }
}
