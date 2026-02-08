package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.utils.strings.Emojis;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO: Add more tests!
public class LocalizationContextTest {
    private static GameMessages gameMessages;

    @BeforeAll
    static void setUp() {
        LocalizationContext localizationContext = new LocalizationContext(LocaleType.ENGLISH);
        gameMessages = localizationContext.getMessages(MessageType.GAME);
    }

    @Test
    @DisplayName("getGameMessages() returns expected English win message")
    void getGameMessagesReturnsExpectedEnglishWinMessage() {
        String ANSWER = "RGBW";
        assertEquals("You Won! " + Emojis.CELEBRATION_TADA + "\n" +
                        "You are the Mastermind!\n" +
                        "Solution was: " + InputVisualRepresentation.getVisualRepresentation(ANSWER),
                gameMessages.getWinMessage(ANSWER));
    }
}
