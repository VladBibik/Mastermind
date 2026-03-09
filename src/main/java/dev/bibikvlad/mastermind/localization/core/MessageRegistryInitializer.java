package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.factories.*;
import dev.bibikvlad.mastermind.localization.messages.error.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.game.GameMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.MainMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.SettingsMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.language.LanguageSelectionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.color.ColorMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.stats.StatsMessages;

public class MessageRegistryInitializer {
    public static MessageFactoryRegistry createAndPopulateRegistry() {
        MessageFactoryRegistry messageFactoryRegistry = new MessageFactoryRegistry();

        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                MainMenuMessages.class,
                "i18n.main_menu",
                new ConsoleMainMenuMessageFactory()
        ));
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                GameMenuMessages.class,
                "i18n.game_menu",
                new ConsoleGameMenuMessageFactory()
        ));
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                GameMessages.class,
                "i18n.game_messages",
                new ConsoleGameMessageFactory()
        ));
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                StatsMessages.class,
                "i18n.player_stats",
                new ConsoleStatsMessageFactory()
        ));
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                SettingsMenuMessages.class,
                "i18n.settings_menu",
                new ConsoleSettingsMessageFactory()
        ));
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                LanguageSelectionMessages.class,
                "i18n.language_selection",
                new ConsoleLanguageMessagesFactory()
        ));
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                ColorMessages.class,
                "i18n.logo_colors",
                new ConsoleColorMessageFactory()
        ));
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                InteractionMessages.class,
                "i18n.interaction_messages",
                new ConsoleInteractionMessageFactory()
        ));

        return messageFactoryRegistry;
    }
}
