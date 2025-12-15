package dev.bibikvlad.mastermind.game.data;

public class GameOutcome {
    private final int turnsPlayed;
    private final GameResult result;

    public GameOutcome(int turnsPlayed, GameResult result) {
        this.turnsPlayed = turnsPlayed;
        this.result = result;
    }

    public int getTurnsPlayed() {
        return turnsPlayed;
    }

    public GameResult getResult() {
        return result;
    }
}
