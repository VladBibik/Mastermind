package dev.bibikvlad.mastermind.app.bootstrap.path;

import java.nio.file.Path;

public class DatabasePathResolver {
    public Path getDatabasePath() {
        DatabaseDeploymentModeFactory factory = new DatabaseDeploymentModeFactory();
        DeploymentModeDetector detector = new DeploymentModeDetector();

        DeploymentMode deploymentMode = detector.detect();
        DatabaseLocationProvider provider = factory.getProvider(deploymentMode);

        return provider.getDatabasePath();
    }
}
