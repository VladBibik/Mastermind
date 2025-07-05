package dev.bibikvlad.mastermind.app;

import dev.bibikvlad.mastermind.chat.language.English;
import dev.bibikvlad.mastermind.chat.language.Language;
import dev.bibikvlad.mastermind.game.MastermindConsoleGame;
import dev.bibikvlad.mastermind.game.RandomAnswerGenerator;

public class MastermindApplication {
    public static void main(String[] args) {
        Language language = new English();

        MastermindConsoleGame game = new MastermindConsoleGame(language, RandomAnswerGenerator.generate());

        game.play();
    }
}
