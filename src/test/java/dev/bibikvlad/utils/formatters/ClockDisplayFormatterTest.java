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
}
