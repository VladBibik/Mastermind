package dev.bibikvlad.utils.strings.logos;

import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

class ColoredAsciiLogoTest {
    @Test
    @DisplayName("Replaces all color placeholders")
    void replacesAllColorPlaceholders() {
        LogoColorsBundle colors = new LogoColorsBundle(ConsoleColor.PINK, ConsoleColor.CYAN, ConsoleColor.DARK_GREEN,
                ConsoleColor.BACKGROUND_BLACK);

        String logo = ColoredAsciiLogo.getLogo(colors);

        assertFalse(logo.contains("<borderColor>"));
        assertFalse(logo.contains("<mainColor>"));
        assertFalse(logo.contains("<accentColor>"));
        assertFalse(logo.contains("<background>"));
        assertFalse(logo.contains("<reset>"));
    }
}
