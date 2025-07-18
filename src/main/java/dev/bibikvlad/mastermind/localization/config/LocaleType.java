package dev.bibikvlad.mastermind.localization.config;

import java.util.HashMap;
import java.util.Optional;

public enum LocaleType {
    ENGLISH(0, "ENGLISH", "EN"),
    RUSSIAN(1, "RUSSIAN", "RU");

    private final int localeIndex;
    private final String languageName;
    private final String locale;

    private static final HashMap<Integer, LocaleType> BY_INDEX = new HashMap<>();
    private static final HashMap<String, LocaleType> BY_LANGUAGE = new HashMap<>();
    private static final HashMap<String, LocaleType> BY_LOCALE = new HashMap<>();

    static {
        for (LocaleType localeType : LocaleType.values()) {
            BY_INDEX.put(localeType.getLocaleIndex(), localeType);
            BY_LANGUAGE.put(localeType.getLanguageName(), localeType);
            BY_LOCALE.put(localeType.getLocale(), localeType);
        }
    }

    LocaleType(int localeIndex, String language, String locale) {
        this.localeIndex = localeIndex;
        this.languageName = language;
        this.locale = locale;
    }

    public int getLocaleIndex() {
        return localeIndex;
    }

    public String getLanguageName() {
        return languageName;
    }

    public String getLocale() {
        return locale;
    }

    public static LocaleType fromLocaleIndex(int localeIndex) {
        return Optional.ofNullable(BY_INDEX.get(localeIndex))
                .orElseThrow(() -> new IllegalArgumentException("Invalid locale index: " + localeIndex));
    }

    public static LocaleType fromLanguageString(String languageName) {
        return Optional.ofNullable(BY_LANGUAGE.get(languageName))
                .orElseThrow(() -> new IllegalArgumentException("Invalid language name string: " + languageName));
    }

    public static LocaleType fromLocaleString(String locale) {
        return Optional.ofNullable(BY_LOCALE.get(locale))
                .orElseThrow(() -> new IllegalArgumentException("Invalid locale string: " + locale));
    }
}
