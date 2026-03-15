package dev.bibikvlad.mastermind.localization.messages.game;

import dev.bibikvlad.mastermind.clues.ClueGenerator;
import dev.bibikvlad.mastermind.clues.InputVisualRepresentation;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.utils.strings.ConsoleColoredValidSymbols;
import dev.bibikvlad.utils.strings.Emojis;
import dev.bibikvlad.utils.strings.GameCluesConstants;
import dev.bibikvlad.utils.strings.logos.ColoredAsciiLogo;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class ConsoleGameMessages implements GameMessages {
    private final ResourceBundle resourceBundle;

    public ConsoleGameMessages(ResourceBundle resourceBundle) {
        this.resourceBundle = resourceBundle;
    }

    @Override
    public String getInvalidInputMessage() {
        return MessageFormat.format(resourceBundle.getString("invalid_input"),
                ConsoleColoredValidSymbols.getSymbols());
    }

    @Override
    public String getIncorrectGuessMessage(int maxTurns, int currentTurn, String answer, String userInput) {
        return MessageFormat.format(resourceBundle.getString("incorrect_guess"),
                String.valueOf(currentTurn + 1), String.valueOf(maxTurns), userInput,
                ClueGenerator.generate(answer, userInput));
    }

    @Override
    public String getGameOverMessage(String answer) {
        return MessageFormat.format(resourceBundle.getString("game_over"),
                InputVisualRepresentation.getVisualRepresentation(answer));
    }

    @Override
    public String getWinMessage(String answer) {
        return MessageFormat.format(resourceBundle.getString("win"), Emojis.CELEBRATION_TADA,
                InputVisualRepresentation.getVisualRepresentation(answer));
    }

    @Override
    public String getRulesMessage() {
        return MessageFormat.format(resourceBundle.getString("rules"),
                ConsoleColoredValidSymbols.getSymbols(), String.valueOf(GameCluesConstants.CIRCLE_SHADED),
                String.valueOf(GameCluesConstants.CIRCLE_EMPTY), String.valueOf(GameCluesConstants.UNDERSCORE));
    }

    //TODO: Move out AsciiLogo and delete methods!
    @Override
    public String getAsciiLogo(LogoColorsBundle logoColorsBundle) {
        return ColoredAsciiLogo.getLogo(logoColorsBundle);
    }
}
