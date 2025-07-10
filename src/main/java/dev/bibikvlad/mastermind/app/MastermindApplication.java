package dev.bibikvlad.mastermind.app;

import dev.bibikvlad.mastermind.localization.GameMessages.EnglishGameLocale;
import dev.bibikvlad.mastermind.localization.GameMessages.GameMessagesLocale;
import dev.bibikvlad.mastermind.game.MastermindConsoleGame;
import dev.bibikvlad.mastermind.game.RandomAnswerGenerator;

public class MastermindApplication {
    public static void main(String[] args) {
        GameMessagesLocale language = new EnglishGameLocale();

        MastermindConsoleGame game = new MastermindConsoleGame(language, RandomAnswerGenerator.generate());

        game.play();
    }
}
