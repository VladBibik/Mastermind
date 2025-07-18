package dev.bibikvlad.mastermind.app;

import dev.bibikvlad.mastermind.game.MastermindConsoleGame;
import dev.bibikvlad.mastermind.game.RandomAnswerGenerator;
import dev.bibikvlad.mastermind.localization.config.LocaleType;
import dev.bibikvlad.mastermind.localization.factory.LocaleManager;
import dev.bibikvlad.mastermind.localization.messages.game.GameMessages;

public class MastermindApplication {
    public static void main(String[] args) {
        GameMessages gameMessages = new LocaleManager(LocaleType.ENGLISH).getGameMessages();

        MastermindConsoleGame game = new MastermindConsoleGame(gameMessages, RandomAnswerGenerator.generate());

        game.play();
    }
}
