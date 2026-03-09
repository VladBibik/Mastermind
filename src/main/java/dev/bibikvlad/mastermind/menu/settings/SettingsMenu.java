package dev.bibikvlad.mastermind.menu.settings;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.localization.messages.interaction.InteractionMessages;
import dev.bibikvlad.mastermind.localization.messages.menu.main.settings.SettingsMenuMessages;
import dev.bibikvlad.mastermind.menu.MainMenu;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.menu.player.LanguageSelectionMenu;
import dev.bibikvlad.mastermind.menu.settings.logo.LogoColorSelectionMenu;

import java.util.Optional;

public class SettingsMenu extends Menu {
    private final Printer printer;
    private final SettingsMenuMessages settingsMenuMessages;
    private final InteractionMessages interactionMessages;

    private boolean showMenuOnNextLoop = true;

    public SettingsMenu(AppContext appContext) {
        super(appContext);

        this.printer = appContext.printer();
        this.settingsMenuMessages = appContext.localizationContext().getMessages(SettingsMenuMessages.class);
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
                .orElse(new MainMenu(appContext));
    }

    private void displayMenu() {
        printer.printMessage(settingsMenuMessages.getMenuOptions());
    }

    private Menu menuOptionSwitcher(int userInputNumber) {
        switch (userInputNumber) {
            case 1 -> {
                return changeLanguage();
            }
            case 2 -> {
                return changeLogoColor();
            }
            case 0 -> {
                return exit();
            }
            default -> {
                printer.printMessage(interactionMessages.getInvalidInputMessage());

                return this;
            }
        }
    }

    private Menu changeLanguage() {
        return new LanguageSelectionMenu(appContext);
    }

    private Menu changeLogoColor() {
        return new LogoColorSelectionMenu(appContext);
    }

    private Menu exit() {
        return new MainMenu(appContext);
    }
}
