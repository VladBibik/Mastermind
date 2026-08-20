package dev.bibikvlad.mastermind.app.bootstrap.TEMP_TEST;

import dev.bibikvlad.mastermind.app.printer.Printer;

public class SoutTest {
    public void test() {
        printSystemConsoleDiagnostics();
        printCharacterTest();
        printAnsiTest();
    }

    private void printSystemConsoleDiagnostics() {
        System.out.println("=== Java Console Diagnostics ===");

        System.out.println("os.name         = " + System.getProperty("os.name"));
        System.out.println("os.version      = " + System.getProperty("os.version"));
        System.out.println("os.arch         = " + System.getProperty("os.arch"));
        System.out.println("java.version    = " + System.getProperty("java.version"));

        System.out.println("file.encoding   = " + System.getProperty("file.encoding"));
        System.out.println("native.encoding = " + System.getProperty("native.encoding"));
        System.out.println("stdout.encoding = " + System.getProperty("stdout.encoding"));
        System.out.println("stdin.encoding  = " + System.getProperty("stdin.encoding"));

        System.out.println("System.console() = " + System.console());

        System.out.println();
    }

    private void printCharacterTest() {
        System.out.println("=== Unicode Tests ===");

        System.out.println("Russian:");
        System.out.println("Русский текст");
        System.out.println("Ларс");

        System.out.println();
        System.out.println("Circles:");
        System.out.println("○");
        System.out.println("◍");
        System.out.println("●");
        System.out.println("⬤");
        System.out.println("◉");

        System.out.println();
        System.out.println("Emoji:");
        System.out.println("😀 😃 😄 🎉 🎯 🏆");

        System.out.println();
        System.out.println("Combined:");
        System.out.println("Русский ○ ◍ ● ⬤ ◉ 😀 🎉");

        System.out.println();
    }

    private void printAnsiTest() {
        System.out.println("=== ANSI Tests ===");

        System.out.println("\u001B[91mRED\u001B[0m");
        System.out.println("\u001B[92mGREEN\u001B[0m");
        System.out.println("\u001B[93mYELLOW\u001B[0m");
        System.out.println("\u001B[94mBLUE\u001B[0m");
        System.out.println("\u001B[95mMAGENTA\u001B[0m");
        System.out.println("\u001B[97mWHITE\u001B[0m");

        System.out.println("\u001B[38;5;170m256 COLOR 170\u001B[0m");
        System.out.println("\u001B[38;5;208m256 COLOR 208\u001B[0m");

        System.out.println("\u001B[40mBLACK BACKGROUND\u001B[0m");

        System.out.println();
    }

    public void testClueSymbols() {
        String[] symbols = {
                "○",
                "◍",
                "●",
                "⬤",
                "◉",
                "😀",
                "🎉"
        };

        for (String symbol : symbols) {
            System.out.printf(
                    "%s | UTF-16 length=%d | code points=%d | U+%04X%n",
                    symbol,
                    symbol.length(),
                    symbol.codePointCount(0, symbol.length()),
                    symbol.codePointAt(0)
            );
        }
    }

    public void testClueSymbols(Printer printer) {
        String[] symbols = {
                "○",
                "◍",
                "●",
                "⬤",
                "◉",
                "😀",
                "🎉"
        };

        for (String symbol : symbols) {
            printer.printMessage(
                    "%s | UTF-16 length=%d | code points=%d | U+%04X%n" +
                    symbol +
                    symbol.length() +
                    symbol.codePointCount(0, symbol.length()) +
                    symbol.codePointAt(0)
            );
        }
    }
}
