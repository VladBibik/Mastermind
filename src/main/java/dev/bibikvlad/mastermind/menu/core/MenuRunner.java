package dev.bibikvlad.mastermind.menu.core;

public class MenuRunner {

    private  MenuRunner() {
        throw new AssertionError("Cannot instantiate MenuRunner");
    }

    public static void runMenu(Menu menu) {

        while (!(menu instanceof ExitMenu)) {
            menu = menu.run();
        }
    }
}
