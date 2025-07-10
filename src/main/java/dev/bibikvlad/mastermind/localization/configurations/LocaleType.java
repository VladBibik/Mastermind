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
}
