package dev.bibikvlad.mastermind.input.interpreter;

import java.util.Set;

public class GlobalMenuCommands {
    public static final Set<String> PLAY = Set.of(
            "play",
            "",
            "start",
            "go",
            "mastermind",
            "1",
            "run",
            "new",
            "new game",
            "launch",
            "execute"
    );
    public static final Set<String> EXIT = Set.of(
            "exit",
            "close",
            "quit",
            "back",
            "return",
            "leave",
            "q",
            "0"
    );
    public static final Set<String> YES = Set.of(
            "yes",
            "y"
    );
}
