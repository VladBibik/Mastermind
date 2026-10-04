package dev.bibikvlad.utils.formatters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ClockDisplayFormatterTest {
    @Test
    @DisplayName("Formats a valid input as a clock-like string")
    void formatsValidInputAsClockLikeString() {
        String clockFormattedString = ClockDisplayFormatter.format(133767);
        String expected = "02:13:767";

        assertEquals(expected, clockFormattedString);
    }

    @Test
    @DisplayName("Returns maximum display value when input exceeds available space")
    void returnsMaximumDisplayValueWhenInputExceedsAvailableSpace() {
        String clockFormattedString = ClockDisplayFormatter.format(4815162342L);
        String expected = "99:99:999";

        assertEquals(expected, clockFormattedString);
    }

    @Test
    @DisplayName("Throws IllegalArgumentException for negative input")
    void throwsIllegalArgumentExceptionForNegativeInput() {
        assertThrows(IllegalArgumentException.class, () -> ClockDisplayFormatter.format(-133767L));
    }

    @Test
    @DisplayName("Formats zero as zero time")
    void formatsZeroAsZeroTime() {
        assertEquals("00:00:000", ClockDisplayFormatter.format(0));
    }

    @Test
    @DisplayName("Formats milliseconds correctly")
    void formatsMillisecondsCorrectly() {
        assertEquals("00:00:999", ClockDisplayFormatter.format(999));
    }
}
