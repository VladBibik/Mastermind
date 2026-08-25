package dev.bibikvlad.mastermind.localization.config;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Represents the localization variants supported by the application.
 *
 * <p>{@link #COMPATIBILITY} is an English localization variant intended for
 * terminals with limited Unicode and ANSI support. It uses compatibility-safe
 * text and console representations while retaining English as its language.
 *
 * <p>The compatibility variant is represented by the synthetic locale
 * {@code en__compat} so that {@link ResourceBundle} can resolve resources
 * using the {@code _en__compat} suffix.
 */
public enum LocalizationType {
    ENGLISH(Locale.ENGLISH),
    RUSSIAN(Locale.of("ru")),
    COMPATIBILITY(new Locale.Builder()
            .setLanguage("en")
            .setVariant("compat")
            .build()
    );

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
