package dev.bibikvlad.mastermind.app.bootstrap;

import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.persistence.player.model.Player;

public class AppContextFactory {
    private final AppContext appContext;

    public AppContextFactory(AppContext appContext) {
        this.appContext = appContext;
    }

    public AppContext recreateWithLocale(LocaleType localeType) {
        Player updatedPlayer = appContext.currentPlayer().withLocale(localeType);
        LocalizationContext newLocalizationContext = new LocalizationContext(localeType);

        return new AppContext(newLocalizationContext, this.appContext.services(),
                this.appContext.printer(), this.appContext.parser(), updatedPlayer);
    }

    public AppContext recreateForNewPlayer(Player newPlayer) {
        return new AppContext(this.appContext.localizationContext(), this.appContext.services(),
                this.appContext.printer(), this.appContext.parser(), newPlayer);
    }
}
