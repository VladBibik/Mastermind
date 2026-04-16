package dev.bibikvlad.mastermind.localization.factories.menu.main.leaderboard;

import dev.bibikvlad.mastermind.localization.core.MessageFactory;
import dev.bibikvlad.mastermind.localization.messages.menu.main.leaderboards.ConsoleLeaderboardMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.leaderboards.LeaderboardMessages;

import java.util.ResourceBundle;

public class LeaderboardMessageFactory implements MessageFactory<LeaderboardMessages> {
    @Override
    public LeaderboardMessages create(ResourceBundle resourceBundle) {
        return new ConsoleLeaderboardMessages(resourceBundle);
    }
}
