package dev.bibikvlad.mastermind.localization.messages.game;

import dev.bibikvlad.mastermind.clues.ClueGenerator;
import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.utils.strings.ConsoleColoredValidSymbols;
import dev.bibikvlad.utils.strings.Emojis;
import dev.bibikvlad.utils.strings.GameCluesConstants;
import dev.bibikvlad.utils.strings.logos.ColoredAsciiLogo;

import java.util.ResourceBundle;

public class ConsoleGameMessages implements GameMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleGameMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getInvalidInputMessage() {
        return resourceBundle.getString("invalid_input")
                .replace("{VALID_SYMBOLS}", ConsoleColoredValidSymbols.getSymbols());
    }

    @Override
    public String getIncorrectGuessMessage(int maxTurns, int currentTurn, String answer, String userInput) {
        return resourceBundle.getString("incorrect_guess")
                .replace("{MAX_TURNS}", String.valueOf(maxTurns))
                .replace("{CURRENT_TURN}", String.valueOf(currentTurn + 1))
                .replace("{USER_INPUT}", userInput)
                .replace("{VISUAL_REPRESENTATION}", ClueGenerator.generate(answer, userInput));
    }

    @Override
    public String getGameOverMessage(String answer) {
        return resourceBundle.getString("game_over")
                .replace("{VISUAL_REPRESENTATION}", InputVisualRepresentation.getVisualRepresentation(answer));
    }

    @Override
    public String getWinMessage(String answer) {
        return resourceBundle.getString("win")
                .replace("{TADA_EMOJI}", Emojis.CELEBRATION_TADA)
                .replace("{ANSWER}", InputVisualRepresentation.getVisualRepresentation(answer));
    }

    @Override
    public String getRulesMessage() {
        return resourceBundle.getString("rules")
                .replace("{VALID_SYMBOLS}", ConsoleColoredValidSymbols.getSymbols())
                .replace("{CIRCLE_SHADED}", String.valueOf(GameCluesConstants.CIRCLE_SHADED))
                .replace("{CIRCLE_EMPTY}", String.valueOf(GameCluesConstants.CIRCLE_EMPTY))
                .replace("{UNDERSCORE}", String.valueOf(GameCluesConstants.UNDERSCORE));
    }

    @Override
    public String getAsciiLogo() {
        return ColoredAsciiLogo.getLogo();
    }
}
