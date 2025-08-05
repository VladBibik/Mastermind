package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.mastermind.localization.messages.game.StubGameMessages;
import dev.bibikvlad.utils.strings.Emojis;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class LocalizationContextTest {
    private final String ANSWER = "RGBW";

    @Test
    @DisplayName("getMessages(MessageType.GAME) returns same GameMessages as getGameMessages()")
    void getMessagesReturnsSameInstanceAsGetGameMessages() {
        LocalizationContext localizationContext = new LocalizationContext(LocaleType.ENGLISH);
        GameMessages messagesFromParametrizedMethod = localizationContext.getMessages(MessageType.GAME);

        assertEquals(localizationContext.getGameMessages().getWinMessage(ANSWER),
                messagesFromParametrizedMethod.getWinMessage(ANSWER));
    }

    @Test
    @DisplayName("getGameMessages() returns expected English win message")
    void getGameMessagesReturnsExpectedEnglishWinMessage() {
        LocalizationContext localizationContext = new LocalizationContext(LocaleType.ENGLISH);
        GameMessages messagesFromGameMethod = localizationContext.getGameMessages();

        assertEquals("You Won! " + Emojis.CELEBRATION_TADA + "\n" +
                        "You are the Mastermind!\n" +
                        "Solution was: " + InputVisualRepresentation.getVisualRepresentation(ANSWER),
                messagesFromGameMethod.getWinMessage(ANSWER));
    }

    @Test
    @DisplayName("getGameMessages() delegates with correct type and bundle name")
    void getGameMessagesDelegatesToProviderWithCorrectParameters() {
        GameMessages dummy = new StubGameMessages();
        FakeMessageProvider fakeMessageProvider = new FakeMessageProvider(dummy);

        LocalizationContext localizationContext = new LocalizationContext(fakeMessageProvider);
        GameMessages result = localizationContext.getGameMessages();

        assertSame(dummy, result);
        assertEquals(GameMessages.class, fakeMessageProvider.lastRequestedType);
        assertEquals("i18n.game_messages", fakeMessageProvider.lastRequestedMessage);
    }

    static class FakeMessageProvider extends MessageProvider {
        private final Object toReturn;
        private Class<?> lastRequestedType;
        private String lastRequestedMessage;

        public FakeMessageProvider(Object toReturn) {
            super(null, null);
            this.toReturn = toReturn;
        }

        @SuppressWarnings("unchecked")
        public <T> T getMessages(Class<T> messageType, String resourceBundleName) {
            this.lastRequestedType = messageType;
            this.lastRequestedMessage = resourceBundleName;

            return (T) toReturn;
        }
    }
}
