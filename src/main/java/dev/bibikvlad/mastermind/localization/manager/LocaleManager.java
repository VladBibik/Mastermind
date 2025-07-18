package dev.bibikvlad.mastermind.localization.manager;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.messages.game.ConsoleGameMessages;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

import java.util.ResourceBundle;

public class LocaleManager {
    private final GameMessages gameMessages;

    public LocaleManager(LocaleType localeType) {
        this.gameMessages = getLocalisedGameMessages(localeType);
    }

    public GameMessages getGameMessages() {
        return gameMessages;
    }

    private GameMessages getLocalisedGameMessages(LocaleType localeType) {
        return switch (localeType) {
            case ENGLISH -> new ConsoleGameMessages(ResourceBundle.getBundle("i18n.game_messages_en"));
            case RUSSIAN -> new ConsoleGameMessages(ResourceBundle.getBundle("i18n.game_messages_ru"));
        };
    }
}
