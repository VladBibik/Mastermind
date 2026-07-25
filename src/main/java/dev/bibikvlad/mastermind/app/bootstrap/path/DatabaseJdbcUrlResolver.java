package dev.bibikvlad.mastermind.app.bootstrap.path;

import dev.bibikvlad.mastermind.app.bootstrap.path.mode.DeploymentMode;
import dev.bibikvlad.mastermind.app.bootstrap.path.mode.DeploymentModeDetector;

public class DatabaseJdbcUrlResolver {
    public String getJdbcUrl() {
        DatabaseLocationProviderFactory factory = new DatabaseLocationProviderFactory();
        DeploymentModeDetector detector = new DeploymentModeDetector();

        DeploymentMode deploymentMode = detector.detect();
        DatabaseLocationProvider provider = factory.getProvider(deploymentMode);

        return "jdbc:sqlite:" + provider.getDatabasePath();
    }
}
