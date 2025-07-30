package dev.bibikvlad.mastermind.app;

import dev.bibikvlad.mastermind.game.MastermindConsoleGame;
import dev.bibikvlad.mastermind.game.RandomAnswerGenerator;

public class MastermindApplication {
    public static void main(String[] args) {
        MastermindConsoleGame game = new MastermindConsoleGame(RandomAnswerGenerator.generate());

        game.play();
    }
}
