package dev.bibikvlad.mastermind.app.context;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.persistence.player.model.Player;

public class AppContextFactory {
    public static AppContext withLocale(AppContext appContext, LocaleType localeType) {
        Player updatedPlayer = appContext.currentPlayer().withLocale(localeType);
        LocalizationContext newLocalizationContext = new LocalizationContext(localeType);

        return new AppContext(newLocalizationContext, appContext.services(),
                appContext.printer(), appContext.parser(), updatedPlayer);
    }

    public static AppContext withColorBundle(AppContext appContext, LogoColorsBundle newLogoBundle) {
        Player updatedPlayer = appContext.currentPlayer().withLogoColorsBundle(newLogoBundle);

        return new AppContext(appContext.localizationContext(), appContext.services(),
                appContext.printer(), appContext.parser(), updatedPlayer);
    }

    public static AppContext withNewPlayer(AppContext appContext, Player newPlayer) {
        return new AppContext(appContext.localizationContext(), appContext.services(),
                appContext.printer(), appContext.parser(), newPlayer);
    }
}
