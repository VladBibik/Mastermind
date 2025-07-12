package dev.bibikvlad.mastermind.localization.configurations;

public enum LocaleType {
    ENGLISH(1, "English", "EN"),
    RUSSIAN(2, "Russian", "RU");

    private final int localeIndex;
    private final String language;
    private final String locale;

    LocaleType(int localeIndex, String language, String locale) {
        this.localeIndex = localeIndex;
        this.language = language;
        this.locale = locale;
    }

    public int getLocaleIndex() {
        return localeIndex;
    }

    public String getLanguage() {
        return language;
    }

    public String getLocale() {
        return locale;
    }

    public static LocaleType fromLocaleIndex(int localeIndex) {
        for (LocaleType localeType : LocaleType.values()) {
            if (localeType.localeIndex == localeIndex) {
                return localeType;
            }
        }

        throw new IllegalArgumentException("Invalid locale index");
    }

    public static LocaleType fromLanguageString(String language) {
        for (LocaleType localeType : LocaleType.values()) {
            if (localeType.language.equals(language)) {
                return localeType;
            }
        }

        throw new IllegalArgumentException("Invalid language string");
    }

    public static LocaleType fromLocaleString(String locale) {
        for (LocaleType localeType : LocaleType.values()) {
            if (localeType.locale.equals(locale)) {
                return localeType;
            }
        }

        throw new IllegalArgumentException("Invalid locale string");
    }
}
