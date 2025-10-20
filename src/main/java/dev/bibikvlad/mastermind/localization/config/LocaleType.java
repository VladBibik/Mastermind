package dev.bibikvlad.mastermind.localization.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum LocaleType {
    ENGLISH(1, "English", "EN"),
    RUSSIAN(2, "Russian", "RU");

    private final int localeIndex;
    private final String languageName;
    private final String locale;

    private static final Map<Integer, LocaleType> BY_INDEX = new HashMap<>();
    private static final Map<String, LocaleType> BY_LANGUAGE = new HashMap<>();
    private static final Map<String, LocaleType> BY_LOCALE = new HashMap<>();

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
        return BY_INDEX.get(localeIndex);
    }

    public static LocaleType fromLanguageString(String languageName) {
        return BY_LANGUAGE.get(languageName.toUpperCase());
    }

    public static LocaleType fromLocaleString(String locale) {
        return BY_LOCALE.get(locale.toUpperCase());
    }
}
