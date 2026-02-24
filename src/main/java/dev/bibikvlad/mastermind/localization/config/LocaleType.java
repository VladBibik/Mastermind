package dev.bibikvlad.mastermind.localization.config;

import java.util.Locale;

public enum LocaleType {
    ENGLISH(Locale.ENGLISH),
    RUSSIAN(Locale.of("ru"));

    private final Locale locale;

    LocaleType(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getNativeDisplayName() {
        return locale.getDisplayName(locale);
    }
}
