package dev.bibikvlad.utils.strings.clue;

public class DefaultClueSymbols implements ClueSymbols {
    @Override
    public char getSymbol(Clue clue) {
        return switch (clue) {
            case EXACT -> '◍';
            case PARTIAL -> '○';
            case NONE -> '_';
        };
    }
}
