package dev.bibikvlad.mastermind.app.bootstrap.path;

import dev.bibikvlad.mastermind.app.bootstrap.MastermindAppLauncher;
import dev.bibikvlad.mastermind.exceptions.PersistenceException;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

public class DeploymentModeDetector {
    public DeploymentMode detect() {
        if (isContainer()) {
            return DeploymentMode.CONTAINER;
        }

        if (isDevelopment()) {
            return DeploymentMode.DEVELOPMENT;
        } else {
            return DeploymentMode.PORTABLE;
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
            throw new PersistenceException("Failed to determine if a location is directory or not.", exception);
        }
    }
}
