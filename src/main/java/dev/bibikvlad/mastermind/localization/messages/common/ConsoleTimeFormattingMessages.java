package dev.bibikvlad.mastermind.localization.messages.common;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ConsoleTimeFormattingMessages implements TimeFormattingMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleTimeFormattingMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getHours(long hour) {
        return MessageFormat.format(resourceBundle.getString("hours"), hour);
    }

    @Override
    public String getMinutes(long minute) {
        return MessageFormat.format(resourceBundle.getString("minutes"), minute);
    }

    @Override
    public String getSeconds(long second) {
        return MessageFormat.format(resourceBundle.getString("seconds"), second);
    }

    @Override
    public String getMilliseconds(long millisecond) {
        return MessageFormat.format(resourceBundle.getString("milliseconds"), millisecond);
    }
}
