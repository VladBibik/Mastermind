package dev.bibikvlad.mastermind.app;

import dev.bibikvlad.mastermind.game.MastermindConsoleGame;
import dev.bibikvlad.mastermind.game.data.GameData;
import dev.bibikvlad.mastermind.game.data.GameOutcome;

public class TimedGameRunner {
    public static GameData launch(MastermindConsoleGame game) {
        long gameStart = System.currentTimeMillis();

        GameOutcome gameOutcome = game.play();

        long gameFinish = System.currentTimeMillis();

        long gameDuration = gameFinish - gameStart;

        return new GameData(gameOutcome, gameDuration);
    }
}
