package dev.bibikvlad.mastermind.localization.config;

import java.util.Locale;

public enum LocalizationType {
    ENGLISH(Locale.ENGLISH),
    RUSSIAN(Locale.of("ru"));

    private final Locale locale;

    LocalizationType(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public String getNativeDisplayName() {
        String displayName = locale.getDisplayLanguage(locale);

        return displayName.substring(0, 1).toUpperCase(locale) + displayName.substring(1);
    }

    public static LocalizationType fromIndex(int index) {
        if (index <= 0 || index > values().length) {
            throw new IllegalArgumentException("Invalid locale index: " + index);
        }

        return values()[index - 1];
    }
}
