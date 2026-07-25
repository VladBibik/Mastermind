package dev.bibikvlad.mastermind.app.bootstrap.path;

import dev.bibikvlad.mastermind.app.bootstrap.path.mode.DeploymentMode;

public class DatabaseLocationProviderFactory {
    public DatabaseLocationProvider getProvider(DeploymentMode deploymentMode) {
        return switch (deploymentMode) {
            case DEVELOPMENT -> new DevelopmentDatabaseLocationProvider();
            case PORTABLE -> new PortableDatabaseLocationProvider();
            case CONTAINER -> new ContainerDatabaseLocationProvider();
        };
    }
}
