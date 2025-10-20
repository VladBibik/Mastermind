package dev.bibikvlad.mastermind.menu.settings;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class LogoColorSelectionMenu {
    private final Player currentPlayer;
    private final PlayerService playerService;
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;

    public LogoColorSelectionMenu(Player currentPlayer, PlayerService playerService,
                                  LocalizationContext localizationContext, MastermindUserInputParser parser) {
        this.currentPlayer = currentPlayer;
        this.playerService = playerService;
        this.localizationContext = localizationContext;
        this.parser = parser;
    }
}
