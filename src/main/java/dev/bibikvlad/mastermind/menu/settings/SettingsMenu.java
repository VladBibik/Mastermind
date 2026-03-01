package dev.bibikvlad.mastermind.menu.settings;

import dev.bibikvlad.mastermind.app.context.AppContext;
import dev.bibikvlad.mastermind.input.interpreter.IntegerInputInterpreter;
import dev.bibikvlad.mastermind.menu.MainMenu;
import dev.bibikvlad.mastermind.menu.Menu;
import dev.bibikvlad.mastermind.menu.player.LanguageSelectionMenu;
import dev.bibikvlad.mastermind.menu.settings.logo.LogoColorSelectionMenu;

import java.util.Optional;

public class SettingsMenu extends Menu {
    private boolean showMenuOnNextLoop = true;

    public SettingsMenu(AppContext appContext) {
        super(appContext);
    }

    @Override
    public Menu run() {
        if (showMenuOnNextLoop) {
            displayMenu();

            showMenuOnNextLoop = false;
        }

        Optional<Integer> selection = IntegerInputInterpreter.readSelection(appContext.parser());

        if (selection.isEmpty())
            return new MainMenu(appContext);

        return menuOptionSwitcher(selection.get());
    }

    private void displayMenu() {
        System.out.println("1. Language");
        System.out.println("2. Logo colors");
        System.out.println("0. Back");
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
                System.out.println("Invalid selection. Please enter a number corresponding to the menu option.");

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
