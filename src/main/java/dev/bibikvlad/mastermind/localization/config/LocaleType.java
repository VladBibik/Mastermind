package dev.bibikvlad.mastermind.localization.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public enum LocaleType {
    ENGLISH(1, Locale.ENGLISH),
    RUSSIAN(2, Locale.of("ru"));

    private final int localeIndex;
    private final Locale locale;

    private static final Map<Integer, LocaleType> BY_INDEX = new HashMap<>();
    private static final Map<String, LocaleType> BY_LOCALE_TAG = new HashMap<>();

    static {
        for (LocaleType localeType : LocaleType.values()) {
            BY_INDEX.put(localeType.getLocaleIndex(), localeType);
            BY_LOCALE_TAG.put(localeType.getLocale().getLanguage().toLowerCase(), localeType);
        }
    }

    LocaleType(int localeIndex, Locale locale) {
        this.localeIndex = localeIndex;
        this.locale = locale;
    }

    public int getLocaleIndex() {
        return localeIndex;
    }

    public Locale getLocale() {
        return locale;
    }

    public static LocaleType fromLocaleIndex(int localeIndex) {
        return Optional.ofNullable(BY_INDEX.get(localeIndex)).orElseThrow(
                () -> new IllegalArgumentException("Invalid LocaleIndex: " + localeIndex));
    }

    public static LocaleType fromLocale(Locale locale) {
        return Optional.ofNullable(BY_LOCALE_TAG.get(locale.getLanguage().toLowerCase())).orElseThrow(
                () -> new IllegalArgumentException("Invalid Locale: " + locale));
    }
}
