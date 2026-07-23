package dev.bibikvlad.mastermind.app.bootstrap.path;

public class DatabasePathResolver {
    private boolean isNotContainer() {
        return System.getenv("MASTERMIND_RUNTIME").isEmpty();
    }
}
