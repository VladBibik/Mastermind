package dev.bibikvlad.mastermind.app.bootstrap.path;

import java.nio.file.Path;

public class DatabaseDeploymentModeFactory {
    public static Path getPath(DeploymentMode deploymentMode) {
        switch (deploymentMode) {
            case DEVELOPMENT -> {
                return new DevelopmentDatabaseLocationProvider().getDatabasePath();
            }
            case PORTABLE -> {
                return new PortableDatabaseLocationProvider().getDatabasePath();
            }
            case CONTAINER -> {
                return new ContainerDatabaseLocationProvider().getDatabasePath();
            }
            default -> throw new IllegalArgumentException("Unknown deployment mode " + deploymentMode);
        }
    }
}
