package dev.bibikvlad.utils.formatters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClockDisplayFormatterTest {
    @Test
    @DisplayName("A correct input return clock-like formatted String")
    void correctInputReturnClockFormattedString() {
        String clockFormattedString = ClockDisplayFormatter.format(133767);
        String expected = "02:13:767";

        assertEquals(expected, clockFormattedString);
    }

    @Test
    @DisplayName("Value that overflows max available space formatter returns a string filled with 9s")
    void valueThatOverflowsMaxAvailableSpaceFormatterReturnsAStringFilledWith9s() {
        String clockFormattedString = ClockDisplayFormatter.format(4815162342L);
        String expected = "99:99:999";

        assertEquals(expected, clockFormattedString);
    }
}
