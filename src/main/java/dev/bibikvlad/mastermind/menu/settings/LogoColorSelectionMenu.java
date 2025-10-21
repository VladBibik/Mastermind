package dev.bibikvlad.mastermind.menu.settings;

import dev.bibikvlad.mastermind.game.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.localization.messages.menu.settings.logo.LogoMessages;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;

public class LogoColorSelectionMenu {
    private final Player currentPlayer;
    private final PlayerService playerService;
    private final LocalizationContext localizationContext;
    private final MastermindUserInputParser parser;
    private final LogoMessages logoMessages;

    private LogoColorsBundle logoColorsBundle;

    public LogoColorSelectionMenu(Player currentPlayer, PlayerService playerService,
                                  LocalizationContext localizationContext, MastermindUserInputParser parser) {
        this.currentPlayer = currentPlayer;
        this.playerService = playerService;
        this.localizationContext = localizationContext;
        this.parser = parser;
        this.logoMessages = localizationContext.getLogoMessages();

        this.logoColorsBundle = currentPlayer.getPlayerConfig().getLogoColorsBundle();
    }

    public void selectLogoColors() {

    }
}
