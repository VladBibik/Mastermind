package dev.bibikvlad.mastermind.model.player;

import dev.bibikvlad.mastermind.app.printer.AnsiSafeFormatter;

public class PlayerNameFormatter {
    public static String format(String name) {
        name = PlayerNameNormalizer.normalize(name);

        return AnsiSafeFormatter.isolate(name);
    }
}
