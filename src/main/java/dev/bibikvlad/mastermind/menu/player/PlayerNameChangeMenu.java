package dev.bibikvlad.mastermind.menu.player;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class PlayerNameChangeMenu {
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final PlayerService playerService;
    private final Player currentPlayer;

    public PlayerNameChangeMenu(LocalizationContext localizationContext, MastermindUserInputParser parser,
                                PlayerService playerService, Player currentPlayer) {
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.playerService = playerService;
        this.currentPlayer = currentPlayer;
    }
}
