package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.services.PlayerService;

public class GameMenu {
    private final MastermindUserInputParser parser;
    private final PlayerService playerService;

    public GameMenu(MastermindUserInputParser parser, PlayerService playerService) {
        this.parser = parser;
        this.playerService = playerService;
    }
}
