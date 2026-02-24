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
        String displayName = locale.getDisplayName(locale);

        return displayName.substring(0, 1).toUpperCase() + displayName.substring(1);
    }

    public static LocaleType fromIndex(int index) {
        if (index <= 0 || index >= values().length) {
            throw new IllegalArgumentException("Invalid locale index: " + index);
        }

        return values()[index - 1];
    }
}
