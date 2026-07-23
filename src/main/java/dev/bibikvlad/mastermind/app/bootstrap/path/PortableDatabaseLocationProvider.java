package dev.bibikvlad.mastermind.app.bootstrap.path;

import dev.bibikvlad.mastermind.app.bootstrap.MastermindAppLauncher;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PortableDatabaseLocationProvider implements DatabaseLocationProvider {
    @Override
    public Path getDatabasePath() {
        try {
            Path jarDirectory = Paths.get(MastermindAppLauncher.class
                    .getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI());

            return jarDirectory.resolve("mastermind.db");

            //TODO: Think about exception handling logic!
        } catch (URISyntaxException exception) {
            throw new RuntimeException(exception);
        }
    }
}
