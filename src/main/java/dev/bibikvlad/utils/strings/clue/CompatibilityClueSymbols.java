package dev.bibikvlad.utils.strings.clue;

public class CompatibilityClueSymbols implements ClueSymbols {
    @Override
    public char getSymbol(Clue clue) {
        return switch (clue) {
            case EXACT -> '#';
            case PARTIAL -> 'O';
            case NONE -> '_';
        };
    }
}
