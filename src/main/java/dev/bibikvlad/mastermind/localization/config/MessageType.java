package dev.bibikvlad.mastermind.localization.config;

import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;
import dev.bibikvlad.mastermind.localization.messages.common.TimeFormattingMessages;
import dev.bibikvlad.mastermind.localization.messages.error.ErrorMessages;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.MainMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.game.GameMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.leaderboards.LeaderboardMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.ProfileMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.delete.DeletePlayerMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.profile.selection.PlayerSelectionMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.SettingsMenuMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.language.LanguageSelectionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.logo.LogoColorSelectionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.logo.color.ColorMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.stats.StatsMessages;

public enum MessageType {
    MAIN_MENU(MainMenuMessages.class),
    GAME(GameMessages.class),
    GAME_MENU(GameMenuMessages.class),
    LEADERBOARDS(LeaderboardMessages.class),
    STATS(StatsMessages.class),
    PROFILE(ProfileMenuMessages.class),
    SELECTION(PlayerSelectionMenuMessages.class),
    DELETE(DeletePlayerMenuMessages.class),
    SETTINGS(SettingsMenuMessages.class),
    LANGUAGE_MENU(LanguageSelectionMessages.class),
    LOGO(LogoColorSelectionMessages.class),
    COLOR(ColorMessages.class),
    INTERACTION(InteractionMessages.class),
    ERROR(ErrorMessages.class),
    TIME(TimeFormattingMessages.class);

    private final Class<? extends LocalizedMessages> messageClass;


    MessageType(Class<? extends LocalizedMessages> messageClass) {
        this.messageClass = messageClass;
    }

    public Class<? extends LocalizedMessages> getMessageClass() {
        return messageClass;
    }
}
