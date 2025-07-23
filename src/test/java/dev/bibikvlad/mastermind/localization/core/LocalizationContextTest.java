package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.utils.strings.Emojis;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

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
        GameMessages messagesFromGameMethod = localizationContext.getGameMessages();

        assertEquals("You Won! " + Emojis.CELEBRATION_TADA + "\n" +
                        "You are the Mastermind!\n" +
                        "Solution was: " + InputVisualRepresentation.getVisualRepresentation(ANSWER),
                messagesFromGameMethod.getWinMessage(ANSWER));
    }

    @Test
    void getGameMessagesPassesCorrectParamsToProvider() {
        GameMessages dummy = new StubGameMessages();
        FakeMessageProvider fakeMessageProvider = new FakeMessageProvider(dummy);

        LocalizationContext localizationContext = new LocalizationContext(fakeMessageProvider);
        GameMessages result = localizationContext.getGameMessages();

        assertSame(dummy, result);
        assertEquals(GameMessages.class, fakeMessageProvider.lastRequestedType);
        assertEquals("i18n.game_messages", fakeMessageProvider.lastRequestedMessage);
    }

    static class FakeMessageProvider extends MessageProvider {
        Class<?> lastRequestedType;
        String lastRequestedMessage;
        Object toReturn;

        public FakeMessageProvider(Object toReturn) {
            super(null, null);
            this.toReturn = toReturn;
        }

        public <T> T getMessages(Class<T> messageType, String resourceBundleName) {
            this.lastRequestedType = messageType;
            this.lastRequestedMessage = resourceBundleName;

            return (T) toReturn;
        }
    }
}
