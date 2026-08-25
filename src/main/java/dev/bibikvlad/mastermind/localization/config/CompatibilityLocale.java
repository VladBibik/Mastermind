package dev.bibikvlad.mastermind.localization.config;

import java.util.Locale;

public class CompatibilityLocale {
    public static Locale getCompitabilityLocale() {
        return new Locale.Builder()
                .setLanguage("en")
                .setVariant("compat")
                .build();
    }
}
