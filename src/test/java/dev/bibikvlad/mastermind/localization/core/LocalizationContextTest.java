package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LocalizationContextTest {
    private static GameMessages gameMessages;

    @BeforeAll
    static void setUp() {
        LocalizationContext localizationContext = new LocalizationContext(LocaleType.ENGLISH);
        gameMessages = localizationContext.getMessages(MessageType.GAME);
    }

    @Test
    @DisplayName("getGameMessages() returns expected English win message")
    void getGameMessagesReturnsExpectedEnglishWinMessage() {
        final String ANSWER = "RGBW";
        assertEquals("You Won! \uD83C\uDF89" + "\n" +
                        "You are the Mastermind!\n" +
                        "Solution was: " + InputVisualRepresentation.getVisualRepresentation(ANSWER),
                gameMessages.getWin(ANSWER));
    }

    @Test
    @DisplayName("getMessages() that takes Class<T> messageType as a parameter returns the same messages " +
            "as the getMessages() method that takes MessageType enum as a parameter.")
    void getMessagesReturnsSameMessages() {
        LocalizationContext localizationContext = new LocalizationContext(LocaleType.ENGLISH);
        GameMessages localizedMessages = localizationContext.getMessages(GameMessages.class);

        assertEquals(localizedMessages.getInvalidInput(), gameMessages.getInvalidInput());
    }
}
