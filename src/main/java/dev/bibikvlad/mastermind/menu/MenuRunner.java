package dev.bibikvlad.mastermind.menu;

import dev.bibikvlad.mastermind.input.parser.MastermindUserInputParser;
import dev.bibikvlad.mastermind.localization.core.LocalizationContext;
import dev.bibikvlad.mastermind.services.PlayerService;

public class MenuRunner {
    public static void runMenu(LocalizationContext localizationContext, MastermindUserInputParser parser,
                               PlayerService playerService) {
        Menu menu = new MainMenu(localizationContext, parser, playerService);

        while (!(menu instanceof ExitMenu)) {
            menu = menu.run();
        }
    }
}
