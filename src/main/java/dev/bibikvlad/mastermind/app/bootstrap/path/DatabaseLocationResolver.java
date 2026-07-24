package dev.bibikvlad.mastermind.app.bootstrap.path;

public class DatabaseLocationResolver {
    public String getJdbcUrl() {
        DatabaseDeploymentModeFactory factory = new DatabaseDeploymentModeFactory();
        DeploymentModeDetector detector = new DeploymentModeDetector();

        DeploymentMode deploymentMode = detector.detect();
        DatabaseLocationProvider provider = factory.getProvider(deploymentMode);

        return "jdbc:sqlite:" + provider.getDatabasePath();
    }
}
