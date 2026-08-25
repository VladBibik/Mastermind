package dev.bibikvlad.mastermind.localization.config;

import java.util.Locale;

public class CompatibilityLocale {
    private CompatibilityLocale() {
        throw new AssertionError("Can't instantiate CompatibilityLocale class");
    }

    public static Locale getCompitabilityLocale() {
        return new Locale.Builder()
                .setLanguage("en")
                .setVariant("compat")
                .build();
    }
}
