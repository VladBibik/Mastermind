package dev.bibikvlad.mastermind.localization.messages.common;

import dev.bibikvlad.mastermind.localization.messages.LocalizedMessages;

public interface TimeFormattingMessages extends LocalizedMessages {
    String getHours(long hour);

    String getMinutes(long minute);

    String getSeconds(long second);

    String getMilliseconds(long millisecond);
}
