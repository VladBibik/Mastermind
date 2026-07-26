package dev.bibikvlad.mastermind.input.parser;

public class LowerCaseParser implements Parser {
    private final Parser parser;

    public LowerCaseParser(Parser parser) {
        this.parser = parser;
    }

    @Override
    public String parse() {
        return parser.parse().toLowerCase();
    }
}
