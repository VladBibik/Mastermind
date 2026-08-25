package dev.bibikvlad.utils.strings.clue;

public class DefaultClueSymbols implements ClueSymbols {
    @Override
    public char exact() {
        return '◍';
    }

    @Override
    public char partial() {
        return '○';
    }

    @Override
    public char none() {
        return '_';
    }
}
