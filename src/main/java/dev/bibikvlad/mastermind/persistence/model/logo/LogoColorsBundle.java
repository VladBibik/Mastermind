package dev.bibikvlad.mastermind.persistence.model.logo;

import dev.bibikvlad.mastermind.persistence.model.enums.ConsoleColor;

public record LogoColorsBundle(ConsoleColor logoBorderColor, ConsoleColor logoMainColor, ConsoleColor logoAccentColor,
                               ConsoleColor logoBackgroundColor) {
}
