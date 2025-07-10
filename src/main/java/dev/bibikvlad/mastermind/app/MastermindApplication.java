package dev.bibikvlad.mastermind.app;

import dev.bibikvlad.mastermind.game.MastermindConsoleGame;
import dev.bibikvlad.mastermind.game.RandomAnswerGenerator;
import dev.bibikvlad.mastermind.localization.configurations.LocaleType;
import dev.bibikvlad.mastermind.localization.manager.LocaleManager;
import dev.bibikvlad.mastermind.localization.messages.GameMessagesLocale;

public class MastermindApplication {
    public static void main(String[] args) {
        GameMessagesLocale gameMessagesLocale = new LocaleManager(LocaleType.ENGLISH).getGameMessagesLocale();

        MastermindConsoleGame game = new MastermindConsoleGame(gameMessagesLocale, RandomAnswerGenerator.generate());

        game.play();
    }
}
