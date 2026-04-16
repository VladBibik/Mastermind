package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.factories.common.ConsoleTimeMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.error.ConsoleErrorMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.game.ConsoleGameMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.interaction.ConsoleInteractionMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.menu.main.ConsoleMainMenuMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.menu.main.game.ConsoleGameMenuMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.menu.main.leaderboard.ConsoleLeaderboardMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.menu.main.settings.ConsoleSettingsMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.menu.main.settings.language.ConsoleLanguageMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.menu.main.settings.logo.ConsoleColorMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.menu.main.settings.logo.ConsoleLogoMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.menu.main.stats.ConsoleStatsMessageFactory;
import dev.bibikvlad.mastermind.localization.messages.common.TimeFormattingMessages;
import dev.bibikvlad.mastermind.localization.messages.error.ErrorMessages;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.MainMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.game.GameMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.leaderboards.ConsoleLeaderboardMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.leaderboards.LeaderboardMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.SettingsMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.language.LanguageSelectionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.logo.LogoColorSelectionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.logo.color.ColorMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.stats.StatsMessages;

public class MessageRegistryInitializer {
    public static MessageFactoryRegistry createAndPopulateRegistry() {
        MessageFactoryRegistry messageFactoryRegistry = new MessageFactoryRegistry();

        populateCommonRegistry(messageFactoryRegistry);
        populateErrorRegistry(messageFactoryRegistry);
        populateGameRegistry(messageFactoryRegistry);
        populateMainMenuRegistry(messageFactoryRegistry);
        populateInteractionRegistry(messageFactoryRegistry);

        return messageFactoryRegistry;
    }

    private static void populateCommonRegistry(MessageFactoryRegistry messageFactoryRegistry) {
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                TimeFormattingMessages.class,
                "i18n.common.time_formatting",
                new ConsoleTimeMessageFactory()
        ));
    }

    private static void populateErrorRegistry(MessageFactoryRegistry messageFactoryRegistry) {
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                ErrorMessages.class,
                "i18n.error.error_messages",
                new ConsoleErrorMessageFactory()
        ));
    }

    private static void populateGameRegistry(MessageFactoryRegistry messageFactoryRegistry) {
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                GameMessages.class,
                "i18n.game.game_messages",
                new ConsoleGameMessageFactory()
        ));
    }

    private static void populateMainMenuRegistry(MessageFactoryRegistry messageFactoryRegistry) {
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                MainMenuMessages.class,
                "i18n.menu.main.main_menu",
                new ConsoleMainMenuMessageFactory()
        ));

        populateGameMenuRegistry(messageFactoryRegistry);
        populateLeaderboardsRegistry(messageFactoryRegistry);
        populateStatsRegistry(messageFactoryRegistry);
        populateSettingsRegistry(messageFactoryRegistry);
    }

    private static void populateGameMenuRegistry(MessageFactoryRegistry messageFactoryRegistry) {
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                GameMenuMessages.class,
                "i18n.menu.main.game.game_menu",
                new ConsoleGameMenuMessageFactory()
        ));
    }

    private static void populateLeaderboardsRegistry(MessageFactoryRegistry messageFactoryRegistry) {
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                LeaderboardMessages.class,
                "i18n.menu.main.leaderboards.leaderboard_menu",
                new ConsoleLeaderboardMessageFactory()
        ));
    }

    private static void populateStatsRegistry(MessageFactoryRegistry messageFactoryRegistry) {
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                StatsMessages.class,
                "i18n.menu.main.stats.player_stats",
                new ConsoleStatsMessageFactory()
        ));
    }

    private static void populateSettingsRegistry(MessageFactoryRegistry messageFactoryRegistry) {
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                SettingsMenuMessages.class,
                "i18n.menu.main.settings.settings_menu",
                new ConsoleSettingsMessageFactory()
        ));
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                LanguageSelectionMessages.class,
                "i18n.menu.main.settings.language.language_selection",
                new ConsoleLanguageMessageFactory()
        ));
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                LogoColorSelectionMessages.class,
                "i18n.menu.main.settings.logo.logo_color_selection",
                new ConsoleLogoMessageFactory()
        ));
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                ColorMessages.class,
                "i18n.menu.main.settings.logo.color.logo_colors",
                new ConsoleColorMessageFactory()
        ));
    }

    private static void populateInteractionRegistry(MessageFactoryRegistry messageFactoryRegistry) {
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                InteractionMessages.class,
                "i18n.interaction.interaction_messages",
                new ConsoleInteractionMessageFactory()
        ));
    }
}
