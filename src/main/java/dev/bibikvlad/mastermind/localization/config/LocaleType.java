package dev.bibikvlad.mastermind.localization.config;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public enum LocaleType {
    ENGLISH(1, "English", Locale.ENGLISH),
    RUSSIAN(2, "Russian", Locale.of("ru"));

    private final int localeIndex;
    private final String languageName;
    private final Locale locale;

    private static final Map<Integer, LocaleType> BY_INDEX = new HashMap<>();
    private static final Map<String, LocaleType> BY_LANGUAGE = new HashMap<>();
    private static final Map<Locale, LocaleType> BY_LOCALE = new HashMap<>();

    static {
        for (LocaleType localeType : LocaleType.values()) {
            BY_INDEX.put(localeType.getLocaleIndex(), localeType);
            BY_LANGUAGE.put(localeType.getLanguageName().toUpperCase(), localeType);
            BY_LOCALE.put(localeType.getLocale(), localeType);
        }
    }

    LocaleType(int localeIndex, String language, Locale locale) {
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

    public Locale getLocale() {
        return locale;
    }

    public static LocaleType fromLocaleIndex(int localeIndex) {
        return Optional.ofNullable(BY_INDEX.get(localeIndex)).orElseThrow(
                () -> new IllegalArgumentException("Invalid LocaleIndex: " + localeIndex));
    }

    public static LocaleType fromLanguageString(String languageName) {
        return Optional.ofNullable(BY_LANGUAGE.get(languageName.toUpperCase())).orElseThrow(
                () -> new IllegalArgumentException("Invalid Language Name: " + languageName));
    }

    public static LocaleType fromLocaleString(Locale locale) {
        return Optional.ofNullable(BY_LOCALE.get(locale)).orElseThrow(
                () -> new IllegalArgumentException("Invalid Locale: " + locale));
    }
}
