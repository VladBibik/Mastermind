package dev.bibikvlad.mastermind.app.bootstrap;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.persistence.player.model.Player;

public class AppContextFactory {
    public static AppContext withLocale(AppContext appContext, LocaleType localeType) {
        Player updatedPlayer = appContext.currentPlayer().withLocale(localeType);
        LocalizationContext newLocalizationContext = new LocalizationContext(localeType);

        return new AppContext(newLocalizationContext, appContext.services(),
                appContext.printer(), appContext.parser(), updatedPlayer);
    }

    public static AppContext withNewPlayer(AppContext appContext, Player newPlayer) {
        return new AppContext(appContext.localizationContext(), appContext.services(),
                appContext.printer(), appContext.parser(), newPlayer);
    }
}
