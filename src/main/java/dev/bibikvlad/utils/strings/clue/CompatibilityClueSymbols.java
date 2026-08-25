package dev.bibikvlad.utils.strings.clue;

public class CompatibilityClueSymbols implements ClueSymbols {
    @Override
    public char exact() {
        return '#';
    }

    @Override
    public char partial() {
        return 'O';
    }

    @Override
    public char none() {
        return '_';
    }
}
