package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.services.PlayerService;

public class PlayerSelectionMenu {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final PlayerService playerService;

    public PlayerSelectionMenu(LocalizationContext localizationContext, MastermindUserInputParser parser,
                               PlayerService playerService) {
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.playerService = playerService;
    }
}
