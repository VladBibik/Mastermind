package dev.bibikvlad.mastermind.game;

public class GameStateManager {
    private final int maxTurns;

    private int attempts = 0;

    public GameStateManager(int maxTurns) {
        this.maxTurns = maxTurns;
    }

    public boolean isOver() {
        return attempts >= maxTurns;
    }

    public void nextTurn() {
        attempts++;
    }

    public int getCurrentTurn() {
        return attempts;
    }
}
