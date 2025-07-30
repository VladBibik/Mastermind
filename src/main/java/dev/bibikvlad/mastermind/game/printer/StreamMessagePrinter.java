package dev.bibikvlad.mastermind.game.printer;

import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

import java.io.PrintStream;

public class StreamMessagePrinter implements MastermindMessagePrinter {
    private final GameMessages gameMessages;
    private final PrintStream printStream;

    public StreamMessagePrinter(GameMessages gameMessages, PrintStream printStream) {
        this.gameMessages = gameMessages;
        this.printStream = printStream;
    }

    @Override
    public void printInvalidInputMessage() {
        printStream.println(gameMessages.getInvalidInputMessage());
    }

    @Override
    public void printIncorrectGuessMessage(int maxTurns, int currentTurn, String answer, String userInput) {
        printStream.println(gameMessages.getIncorrectGuessMessage(maxTurns, currentTurn, answer, userInput));
    }

    @Override
    public void printGameOverMessage(String answer) {
        printStream.println(gameMessages.getGameOverMessage(answer));
    }

    @Override
    public void printWinMessage(String answer) {
        printStream.println(gameMessages.getWinMessage(answer));
    }

    @Override
    public void printRulesMessage() {
        printStream.println(gameMessages.getRulesMessage());
    }

    @Override
    public void printAsciiLogo() {
        printStream.println(gameMessages.getAsciiLogo());
    }
}
