package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.localization.factories.ConsoleErrorMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.ConsoleGameMessageFactory;
import dev.bibikvlad.mastermind.localization.factories.ConsoleLogoMessagesFactory;
import dev.bibikvlad.mastermind.localization.factories.ConsoleMainMenuMessageFactory;
import dev.bibikvlad.mastermind.localization.messages.error.ErrorMessages;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.MainMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.LogoMessages;

public class MessageRegistryInitializer {
    public static MessageFactoryRegistry createAndPopulateRegistry() {
        MessageFactoryRegistry messageFactoryRegistry = new MessageFactoryRegistry();

        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                GameMessages.class,
                "i18n.game_messages",
                new ConsoleGameMessageFactory()
        ));
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                ErrorMessages.class,
                "i18n.error_messages",
                new ConsoleErrorMessageFactory()
        ));
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                MainMenuMessages.class,
                "i18n.main_menu",
                new ConsoleMainMenuMessageFactory()
        ));
        messageFactoryRegistry.register(new LocalizedMessageConfig<>(
                LogoMessages.class,
                "i18n.logo_colors",
                new ConsoleLogoMessagesFactory()
        ));

        return messageFactoryRegistry;
    }
}
