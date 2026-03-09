package dev.bibikvlad.mastermind.localization.factories.menu.main.stats;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.menu.main.stats.ConsoleStatsMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.stats.StatsMessages;

import java.util.ResourceBundle;

public class ConsoleStatsMessageFactory implements MessageFactory<StatsMessages> {
    @Override
    public StatsMessages create(ResourceBundle resourceBundle) {
        return new ConsoleStatsMessages(resourceBundle);
    }
}
