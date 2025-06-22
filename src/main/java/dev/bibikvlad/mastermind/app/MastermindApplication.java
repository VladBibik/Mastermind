package dev.bibikvlad.mastermind.app;

import dev.bibikvlad.mastermind.game.MastermindConsoleGame;

public class MastermindApplication {
    public static void main(String[] args) {
        MastermindConsoleGame game = new MastermindConsoleGame();

        game.play();
    }
}
