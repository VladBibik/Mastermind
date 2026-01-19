package dev.bibikvlad.mastermind.game.presentation;

import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;

public class GameMessagePrinter {
    private final Printer printer;
    private final GameMessages gameMessages;

    public GameMessagePrinter(Printer printer, GameMessages gameMessages) {
        this.printer = printer;
        this.gameMessages = gameMessages;
    }

    public void printInvalidInputMessage() {
        printer.printMessage(gameMessages.getInvalidInputMessage());
    }

    public void printIncorrectGuessMessage(int maxTurns, int currentTurn, String answer, String userInput) {
        printer.printMessage(gameMessages.getIncorrectGuessMessage(maxTurns, currentTurn, answer, userInput));
    }

    public void printGameOverMessage(String answer) {
        printer.printMessage(gameMessages.getGameOverMessage(answer));
    }

    public void printWinMessage(String answer) {
        printer.printMessage(gameMessages.getWinMessage(answer));
    }

    public void printRulesMessage() {
        printer.printMessage(gameMessages.getRulesMessage());
    }

    public void printAsciiLogo(LogoColorsBundle logoColorsBundle) {
        printer.printMessage(gameMessages.getAsciiLogo(logoColorsBundle));
    }
}
