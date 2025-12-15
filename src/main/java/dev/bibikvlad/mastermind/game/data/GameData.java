package dev.bibikvlad.mastermind.game.data;

public class GameData {
    private final GameOutcome gameOutcome;
    private final long gameDuration;

    public GameData(GameOutcome gameOutcome, long gameDuration) {
        this.gameOutcome = gameOutcome;
        this.gameDuration = gameDuration;
    }

    public GameOutcome getGameOutcome() {
        return gameOutcome;
    }

    public long getGameDuration() {
        return gameDuration;
    }
}
