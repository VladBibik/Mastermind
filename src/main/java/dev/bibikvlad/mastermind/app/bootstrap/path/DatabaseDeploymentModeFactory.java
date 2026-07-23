package dev.bibikvlad.mastermind.app.bootstrap.path;

public class DatabaseDeploymentModeFactory {
    public DatabaseLocationProvider getProvider(DeploymentMode deploymentMode) {
        switch (deploymentMode) {
            case DEVELOPMENT -> {
                return new DevelopmentDatabaseLocationProvider();
            }
            case PORTABLE -> {
                return new PortableDatabaseLocationProvider();
            }
            case CONTAINER -> {
                return new ContainerDatabaseLocationProvider();
            }
            default -> throw new IllegalArgumentException("Unknown deployment mode " + deploymentMode);
        }
    }
}
