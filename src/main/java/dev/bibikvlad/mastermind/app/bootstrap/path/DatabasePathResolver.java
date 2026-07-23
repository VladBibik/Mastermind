package dev.bibikvlad.mastermind.app.bootstrap.path;

import dev.bibikvlad.mastermind.app.bootstrap.MastermindAppLauncher;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

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
        try {
            URI location = MastermindAppLauncher.class.getProtectionDomain().getCodeSource().getLocation().toURI();

            return Files.isDirectory(Path.of(location));
        } catch (URISyntaxException exception) {
            //TODO: Think about exception handling logic!
            throw new RuntimeException(exception);
        }
    }
}
