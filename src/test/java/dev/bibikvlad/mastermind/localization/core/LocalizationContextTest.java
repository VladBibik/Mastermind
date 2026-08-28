package dev.bibikvlad.mastermind.localization.core;

import dev.bibikvlad.mastermind.game.mode.DefaultGamePresentation;
import dev.bibikvlad.mastermind.game.mode.GamePresentation;
import dev.bibikvlad.mastermind.localization.config.LocalizationType;
import dev.bibikvlad.mastermind.localization.config.MessageType;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.utils.strings.ConsoleColoredValidSymbols;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//TODO: private static or BeforeAll? Do research!
class LocalizationContextTest {
    private static final LocalizationContext localizationContext = new LocalizationContext(LocalizationType.ENGLISH);
    private static final GameMessages gameMessages = localizationContext.getMessages(MessageType.GAME);
    private static final GamePresentation GAME_PRESENTATION = new DefaultGamePresentation(
            new LogoColorsBundle(
                    ConsoleColor.PINK,
                    ConsoleColor.CYAN,
                    ConsoleColor.DARK_GREEN,
                    ConsoleColor.BACKGROUND_BLACK
            )
    );

    @Test
    @DisplayName("getGameMessages() returns expected English win message")
    void getGameMessagesReturnsExpectedEnglishWinMessage() {
        final String ANSWER = "RGBW";
        assertEquals("You Won! \uD83C\uDF89" + "\n" +
                        "You are the Mastermind!\n" +
                        "Solution was: " + GAME_PRESENTATION.getVisualRepresentation(ANSWER),
                gameMessages.getWin(GAME_PRESENTATION.getVisualRepresentation(ANSWER)));
    }

    @Test
    @DisplayName("getMessages() that takes Class<T> messageType as a parameter returns the same messages " +
            "as the getMessages() method that takes MessageType enum as a parameter.")
    void getMessagesReturnsSameMessages() {
        LocalizationContext localizationContext = new LocalizationContext(LocalizationType.ENGLISH);
        GameMessages localizedMessages = localizationContext.getMessages(GameMessages.class);
        String validSymbols = ConsoleColoredValidSymbols.getSymbols();

        assertEquals(localizedMessages.getInvalidInput(validSymbols), gameMessages.getInvalidInput(validSymbols));
    }
}
