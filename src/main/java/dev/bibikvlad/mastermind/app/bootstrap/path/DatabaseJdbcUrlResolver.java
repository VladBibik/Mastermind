package dev.bibikvlad.mastermind.app.bootstrap.path;

public class DatabaseJdbcUrlResolver {
    public String getJdbcUrl() {
        DatabaseLocationProviderFactory factory = new DatabaseLocationProviderFactory();
        DeploymentModeDetector detector = new DeploymentModeDetector();

        DeploymentMode deploymentMode = detector.detect();
        DatabaseLocationProvider provider = factory.getProvider(deploymentMode);

        return "jdbc:sqlite:" + provider.getDatabasePath();
    }
}
