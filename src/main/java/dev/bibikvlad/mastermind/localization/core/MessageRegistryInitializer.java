package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.factories.game.ConsoleGameMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.interaction.ConsoleInteractionMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.menu.main.ConsoleMainMenuMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.menu.main.game.ConsoleGameMenuMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.menu.main.settings.ConsoleSettingsMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.menu.main.settings.language.ConsoleLanguageMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.menu.main.settings.logo.ConsoleColorMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.menu.main.settings.logo.ConsoleLogoMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.menu.main.stats.ConsoleStatsMessageFactory;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.game.GameMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.MainMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.SettingsMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.language.LanguageSelectionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.logo.LogoColorSelectionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.logo.color.ColorMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.stats.StatsMessages;

public class MessageRegistryInitializer {
    public static MessageFactoryRegistry createAndPopulateRegistry() {
        MessageFactoryRegistry messageFactoryRegistry = new MessageFactoryRegistry();

        populateGameRegistry(messageFactoryRegistry);
        populateMainMenuRegistry(messageFactoryRegistry);
        populateInteractionRegistry(messageFactoryRegistry);

        return messageFactoryRegistry;
    }

    private static void populateGameRegistry(MessageFactoryRegistry messageFactoryRegistry) {
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                GameMessages.class,
                "i18n.game_messages",
                new ConsoleGameMessageFactory()
        ));
    }

    private static void populateMainMenuRegistry(MessageFactoryRegistry messageFactoryRegistry) {
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                MainMenuMessages.class,
                "i18n.main_menu",
                new ConsoleMainMenuMessageFactory()
        ));

        populateGameMenuRegistry(messageFactoryRegistry);
        populateStatsRegistry(messageFactoryRegistry);
        populateSettingsRegistry(messageFactoryRegistry);
    }

    private static void populateGameMenuRegistry(MessageFactoryRegistry messageFactoryRegistry) {
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                GameMenuMessages.class,
                "i18n.game_menu",
                new ConsoleGameMenuMessageFactory()
        ));
    }

    private static void populateStatsRegistry(MessageFactoryRegistry messageFactoryRegistry) {
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                StatsMessages.class,
                "i18n.player_stats",
                new ConsoleStatsMessageFactory()
        ));
    }

    private static void populateSettingsRegistry(MessageFactoryRegistry messageFactoryRegistry) {
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                SettingsMenuMessages.class,
                "i18n.settings_menu",
                new ConsoleSettingsMessageFactory()
        ));
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                LanguageSelectionMessages.class,
                "i18n.language_selection",
                new ConsoleLanguageMessageFactory()
        ));
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                LogoColorSelectionMessages.class,
                "i18n.logo_color_selection",
                new ConsoleLogoMessageFactory()
        ));
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                ColorMessages.class,
                "i18n.logo_colors",
                new ConsoleColorMessageFactory()
        ));
    }

    private static void populateInteractionRegistry(MessageFactoryRegistry messageFactoryRegistry) {
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                InteractionMessages.class,
                "i18n.interaction_messages",
                new ConsoleInteractionMessageFactory()
        ));
    }
}
