package dev.bibikvlad.mastermind.localization.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public enum LocaleType {
    ENGLISH(Locale.ENGLISH),
    RUSSIAN(Locale.of("ru"));

    private final Locale locale;

    private static final Map<String, LocaleType> BY_LOCALE_TAG = new HashMap<>();

    static {
        for (LocaleType localeType : LocaleType.values()) {
            BY_LOCALE_TAG.put(localeType.getLocale().getLanguage().toLowerCase(), localeType);
        }
    }

    LocaleType(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public static LocaleType fromLocale(Locale locale) {
        return Optional.ofNullable(BY_LOCALE_TAG.get(locale.getLanguage().toLowerCase())).orElseThrow(
                () -> new IllegalArgumentException("Invalid Locale: " + locale));
    }
}
