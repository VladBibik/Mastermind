package dev.bibikvlad.mastermind.app.bootstrap.path;

import java.nio.file.Path;

public class DevelopmentDatabaseLocationProvider implements DatabaseLocationProvider {
    @Override
    public Path getDatabasePath() {
        return Path.of(System.getProperty("user.dir")).resolve("mastermind.db");
    }
}
