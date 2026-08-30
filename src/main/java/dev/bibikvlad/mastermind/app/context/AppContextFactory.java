package dev.bibikvlad.mastermind.app.context;

import dev.bibikvlad.mastermind.localization.config.LocalizationType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.model.player.Player;

public class AppContextFactory {
    public static AppContext withLocale(AppContext appContext, LocalizationType localizationType) {
        Player updatedPlayer = appContext.currentPlayer().withLocalizationType(localizationType);
        LocalizationContext newLocalizationContext = new LocalizationContext(localizationType);

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
