package dev.bibikvlad.mastermind.localization.config;

import java.util.HashMap;

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
            BY_LANGUAGE.put(localeType.getLanguage(), localeType);
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

    public String getLanguage() {
        return languageName;
    }

    public String getLocale() {
        return locale;
    }

    public static LocaleType fromLocaleIndex(int localeIndex) {
        LocaleType localeType = BY_INDEX.get(localeIndex);

        if (localeType == null) {
            throw new IllegalArgumentException("Invalid locale index: " + localeIndex);
        }

        return localeType;
    }

    public static LocaleType fromLanguageString(String language) {
        LocaleType localeType = BY_LANGUAGE.get(language);

        if (localeType == null) {
            throw new IllegalArgumentException("Invalid language string: " + language);
        }

        return localeType;
    }

    public static LocaleType fromLocaleString(String locale) {
        LocaleType localeType = BY_LOCALE.get(locale);

        if (localeType == null) {
            throw new IllegalArgumentException("Invalid locale string: " + locale);
        }

        return localeType;
    }
}
