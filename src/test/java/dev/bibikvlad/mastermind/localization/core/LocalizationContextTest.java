package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.utils.strings.Emojis;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocalizationContextTest {
    private final String ANSWER = "RGBW";

    @Test
    @DisplayName("Returns correct messages from MessageType enum")
    void returnsLocaleFromMessageTypeEnum() {
        LocalizationContext localizationContext = new LocalizationContext(LocaleType.ENGLISH);
        GameMessages messagesFromParametrizedMethod = localizationContext.getMessages(MessageType.GAME);

        assertEquals(localizationContext.getGameMessages().getWinMessage(ANSWER),
                messagesFromParametrizedMethod.getWinMessage(ANSWER));
    }

    @Test
    @DisplayName("Returns correct message from get game message method")
    void returnsCorrectMessageFromGetGameMessageMethod() {
        LocalizationContext localizationContext = new LocalizationContext(LocaleType.ENGLISH);
        GameMessages messagesFromGameMethod = localizationContext.getMessages(MessageType.GAME);

        assertEquals("You Won! " + Emojis.CELEBRATION_TADA + "\n" +
                        "You are the Mastermind!\n" +
                        "Solution was: " + InputVisualRepresentation.getVisualRepresentation(ANSWER),
                messagesFromGameMethod.getWinMessage(ANSWER));
    }
}
