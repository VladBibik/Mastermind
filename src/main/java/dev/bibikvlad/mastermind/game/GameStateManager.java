package dev.bibikvlad.mastermind.game;

public class GameStateManager {
    private final int maxTurns;

    private int currentTurn = 0;
    private boolean gameClosed = false;

    public GameStateManager(int maxTurns) {
        this.maxTurns = maxTurns;
    }

    public boolean isOver() {
        return currentTurn >= maxTurns;
    }

    public void nextTurn() {
        currentTurn++;
    }

    public void markClosed() {
        gameClosed = true;
    }

    public boolean isGameClosed() {
        return gameClosed;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }
}
