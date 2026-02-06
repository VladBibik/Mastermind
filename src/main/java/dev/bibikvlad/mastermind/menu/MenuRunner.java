package dev.bibikvlad.mastermind.menu;

public class MenuRunner {
    public static void runMenu(Menu menu) {

        while (!(menu instanceof ExitMenu)) {
            menu = menu.run();
        }
    }
}
