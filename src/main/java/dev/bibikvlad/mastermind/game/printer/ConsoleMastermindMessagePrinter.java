package dev.bibikvlad.mastermind.game.printer;

import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

public class ConsoleMastermindMessagePrinter implements MastermindMessagePrinter {
    private final GameMessages gameMessages;

    public ConsoleMastermindMessagePrinter(GameMessages gameMessages) {
        this.gameMessages = gameMessages;
    }

    @Override
    public void printInvalidInputMessage() {
        System.out.println(gameMessages.getInvalidInputMessage());
    }

    @Override
    public void printIncorrectGuessMessage(int maxTurns, int currentTurn, String answer, String userInput) {
        System.out.println(gameMessages.getIncorrectGuessMessage(maxTurns, currentTurn, answer, userInput));
    }

    @Override
    public void printGameOverMessage(String answer) {
        System.out.println(gameMessages.getGameOverMessage(answer));
    }

    @Override
    public void printWinMessage(String answer) {
        System.out.println(gameMessages.getWinMessage(answer));
    }

    @Override
    public void printRulesMessage() {
        System.out.println(gameMessages.getRulesMessage());
    }

    @Override
    public void printAsciiLogo() {
        System.out.println(gameMessages.getAsciiLogo());
    }
}
