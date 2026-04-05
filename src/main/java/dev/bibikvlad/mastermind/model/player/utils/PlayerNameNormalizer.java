package dev.bibikvlad.mastermind.model.player.utils;

public class PlayerNameNormalizer {
    public static String normalize(String name) {
        return name.replace("\n", "")
                .replace("\r", "")
                .replace("\t", "");
    }
}
