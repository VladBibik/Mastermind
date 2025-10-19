package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.services.PlayerService;

public class FirstTimeLaunch {
    public static void launch(MastermindUserInputParser parser, PlayerService playerService) {
        System.out.println("Welcome to the Mastermind Game!");

        NewPlayerCreation.create(parser, playerService);
    }
}
