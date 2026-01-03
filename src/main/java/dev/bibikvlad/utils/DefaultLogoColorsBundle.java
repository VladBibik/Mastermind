package dev.bibikvlad.utils;

import dev.bibikvlad.mastermind.persistence.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.persistence.model.logo.LogoColorsBundle;

public class DefaultLogoColorsBundle {
    public static LogoColorsBundle get() {
        return new LogoColorsBundle(
                ConsoleColor.ORCHID,
                ConsoleColor.ORANGE,
                ConsoleColor.BRIGHT_RED,
                ConsoleColor.BACKGROUND_BLACK
        );
    }
}
