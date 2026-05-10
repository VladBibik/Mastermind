package dev.bibikvlad.mastermind.menu.main.settings.logo;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.context.AppContextFactory;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.input.parser.Parser;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.logo.LogoColorSelectionMessages;
import dev.bibikvlad.mastermind.menu.core.Menu;
import dev.bibikvlad.mastermind.menu.main.settings.SettingsMenu;
import dev.bibikvlad.mastermind.menu.main.settings.logo.color.ColorSelectionMenu;
import dev.bibikvlad.mastermind.model.enums.ConsoleColor;
import dev.bibikvlad.mastermind.model.logo.LogoColorsBundle;
import dev.bibikvlad.mastermind.model.player.Player;
import dev.bibikvlad.mastermind.services.PlayerService;
import dev.bibikvlad.utils.DefaultLogoColorsBundle;
import dev.bibikvlad.utils.strings.GameCluesConstants;
import dev.bibikvlad.utils.strings.logos.ColoredAsciiLogo;

import java.util.Optional;

public class LogoColorSelectionMenu extends Menu {
    private final Parser parser;
    private final Printer printer;
    private final PlayerService playerService;
    private final ColorSelectionMenu colorSelectionMenu;
    private final Player currentPlayer;
    private final LogoColorSelectionMessages logoMessages;
    private final InteractionMessages interactionMessages;

    private LogoColorsBundle logoColorsBundle;
    private boolean showMenuOnNextLoop = true;

    public LogoColorSelectionMenu(AppContext appContext) {
        super(appContext);

        this.parser = appContext.parser();
        this.printer = appContext.printer();
        this.playerService = appContext.services().getPlayerService();
        this.colorSelectionMenu = new ColorSelectionMenu(appContext);
        this.currentPlayer = appContext.currentPlayer();
        this.logoColorsBundle = currentPlayer.getPlayerConfig().logoColorsBundle();
        this.logoMessages = appContext.localizationContext().getMessages(LogoColorSelectionMessages.class);
        this.interactionMessages = appContext.localizationContext().getMessages(InteractionMessages.class);
    }

    @Override
    public Menu run() {
        if (showMenuOnNextLoop) {
            displayMenu();

            showMenuOnNextLoop = false;
        }

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(appContext.parser());

        return selection
                .map(this::menuOptionSwitcher)
                .orElse(new SettingsMenu(appContext));
    }

    private void displayMenu() {
        printer.printMessage(logoMessages.getMenuOptions());
    }

    private Menu menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> {
                printCurrentLogo();

                return this;
            }
            case 2 -> {
                changeBorderColor();

                return this;
            }
            case 3 -> {
                changeMainColor();

                return this;
            }
            case 4 -> {
                changeAccentColor();

                return this;
            }
            case 5 -> {
                changeBackgroundColor();

                return this;
            }
            case 6 -> {
                resetToDefault();

                return this;
            }
            case 7 -> {
                return saveAndReturnBack();
            }
            case 0 -> {
                return new SettingsMenu(appContext);
            }
            default -> {
                printer.printMessage(interactionMessages.getInvalidInput());

                return this;
            }
        }
    }

    private void printCurrentLogo() {
        printer.printMessage(ColoredAsciiLogo.getLogo(logoColorsBundle));
        printer.printMessage(interactionMessages.getPressEnter());

        parser.parseUserInput();

        showMenuOnNextLoop = true;
    }

    private void changeBorderColor() {
        ConsoleColor borderColor = colorSelectionMenu.selectForegroundColor();

        if (borderColor == null) {
            return;
        }

        logoColorsBundle = logoColorsBundle.withLogoBorderColor(borderColor);

        printer.printMessage(logoMessages.getMenuBorderChanged(getForegroundPreview(borderColor)));

        waitForUserConfirmation();
    }

    private void changeMainColor() {
        ConsoleColor mainColor = colorSelectionMenu.selectForegroundColor();

        if (mainColor == null) {
            return;
        }

        logoColorsBundle = logoColorsBundle.withLogoMainColor(mainColor);

        printer.printMessage(logoMessages.getMenuMainChanged(getForegroundPreview(mainColor)));

        waitForUserConfirmation();
    }

    private void changeAccentColor() {
        ConsoleColor accentColor = colorSelectionMenu.selectForegroundColor();

        if (accentColor == null) {
            return;
        }

        logoColorsBundle = logoColorsBundle.withLogoAccentColor(accentColor);

        printer.printMessage(logoMessages.getMenuAccentChanged(getForegroundPreview(accentColor)));

        waitForUserConfirmation();
    }

    private void changeBackgroundColor() {
        ConsoleColor backgroundColor = colorSelectionMenu.selectBackgroundColor();

        if (backgroundColor == null) {
            return;
        }

        logoColorsBundle = logoColorsBundle.withLogoBackgroundColor(backgroundColor);

        printer.printMessage(logoMessages.getMenuBackgroundChanged(getBackgroundPreview(backgroundColor)));

        waitForUserConfirmation();
    }

    private String getForegroundPreview(ConsoleColor color) {
        return color.getCode() + GameCluesConstants.CIRCLE_SOLID + ConsoleColor.RESET.getCode();
    }

    private String getBackgroundPreview(ConsoleColor color) {
        return color.getCode() + "    " + ConsoleColor.RESET.getCode();
    }

    private void waitForUserConfirmation() {
        printer.printMessage(interactionMessages.getPressEnter());

        showMenuOnNextLoop = true;

        parser.parseUserInput();
    }

    private void resetToDefault() {
        logoColorsBundle = DefaultLogoColorsBundle.get();
    }

    private Menu saveAndReturnBack() {
        playerService.updateLogoColors(currentPlayer.getId(), logoColorsBundle);

        return new SettingsMenu(AppContextFactory.withColorBundle(appContext, logoColorsBundle));
    }
}
