package dev.bibikvlad.mastermind.localization.messages.common;

import java.util.ResourceBundle;

public class ConsoleTimeFormattingMessages implements TimeFormattingMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleTimeFormattingMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getHours(long hour) {
        return resourceBundle.getString("hours");
    }

    @Override
    public String getMinutes(long minute) {
        return resourceBundle.getString("minutes");
    }

    @Override
    public String getSeconds(long second) {
        return resourceBundle.getString("seconds");
    }

    @Override
    public String getMillisecond(long millisecond) {
        return resourceBundle.getString("milliseconds");
    }
}
