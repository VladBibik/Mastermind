package dev.bibikvlad.mastermind.app.bootstrap.path;

import dev.bibikvlad.mastermind.app.bootstrap.MastermindAppLauncher;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DatabasePathResolver {
    public Path getDatabasePath() {
        DatabaseDeploymentModeFactory factory = new DatabaseDeploymentModeFactory();

        if (isContainer()) {
            return factory.getPath(DeploymentMode.CONTAINER);
        }

        if (isDevelopment()) {
            return factory.getPath(DeploymentMode.DEVELOPMENT);
        } else {
            return factory.getPath(DeploymentMode.PORTABLE);
        }
    }

    private boolean isContainer() {
        return System.getenv("MASTERMIND_RUNTIME") != null;
    }

    private boolean isDevelopment() {
        URL location = MastermindAppLauncher.class.getProtectionDomain().getCodeSource().getLocation();
        Path path = Paths.get(location.getPath());

        return Files.isDirectory(path);
    }
}
