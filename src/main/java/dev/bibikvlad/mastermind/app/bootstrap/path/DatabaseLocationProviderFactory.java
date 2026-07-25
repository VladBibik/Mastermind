package dev.bibikvlad.mastermind.app.bootstrap.path;

import dev.bibikvlad.mastermind.app.bootstrap.path.mode.DeploymentMode;
import dev.bibikvlad.mastermind.app.bootstrap.path.provider.ContainerDatabaseLocationProvider;
import dev.bibikvlad.mastermind.app.bootstrap.path.provider.DatabaseLocationProvider;
import dev.bibikvlad.mastermind.app.bootstrap.path.provider.DevelopmentDatabaseLocationProvider;
import dev.bibikvlad.mastermind.app.bootstrap.path.provider.PortableDatabaseLocationProvider;

public class DatabaseLocationProviderFactory {
    public DatabaseLocationProvider getProvider(DeploymentMode deploymentMode) {
        return switch (deploymentMode) {
            case DEVELOPMENT -> new DevelopmentDatabaseLocationProvider();
            case PORTABLE -> new PortableDatabaseLocationProvider();
            case CONTAINER -> new ContainerDatabaseLocationProvider();
        };
    }
}
