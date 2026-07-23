package dev.bibikvlad.mastermind.app.bootstrap.path;

import dev.bibikvlad.mastermind.app.bootstrap.MastermindAppLauncher;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DatabasePathResolver {
    private boolean isNotContainer() {
        return System.getenv("MASTERMIND_RUNTIME").isEmpty();
    }

    private boolean isDevelopment() {
        URL location = MastermindAppLauncher.class.getProtectionDomain().getCodeSource().getLocation();
        Path path = Paths.get(location.getPath());

        return Files.isDirectory(path);
    }
}
