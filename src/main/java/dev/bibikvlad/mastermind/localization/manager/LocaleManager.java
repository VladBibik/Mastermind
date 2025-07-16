package dev.bibikvlad.mastermind.localization.manager;

import dev.bibikvlad.mastermind.localization.configurations.LocaleType;
import dev.bibikvlad.mastermind.localization.messages.ConsoleGameMessages;
import dev.bibikvlad.mastermind.localization.messages.GameMessages;

import java.util.ResourceBundle;

public class LocaleManager {
    private GameMessages gameMessages;

    public LocaleManager(LocaleType localeType) {
        getLocalisedGameMessages(localeType);
    }

    public GameMessages getGameMessages() {
        return gameMessages;
    }

    private void getLocalisedGameMessages(LocaleType localeType) {
        this.gameMessages = switch (localeType) {
            case ENGLISH -> new ConsoleGameMessages(ResourceBundle.getBundle("i18n.game_messages_en"));
            case RUSSIAN -> new ConsoleGameMessages(ResourceBundle.getBundle("i18n.game_messages_ru"));
        };
    }
}
