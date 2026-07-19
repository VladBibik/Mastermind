package dev.bibikvlad.utils;

import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;

public class DefaultLogoColorsBundle {
    private DefaultLogoColorsBundle() {
        throw new AssertionError("Utility class");
    }

    public static LogoColorsBundle get() {
        return new LogoColorsBundle(
                ConsoleColor.ORCHID,
                ConsoleColor.ORANGE,
                ConsoleColor.BRIGHT_RED,
                ConsoleColor.BACKGROUND_BLACK
        );
    }
}
