package dev.bibikvlad.mastermind.app.bootstrap.path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ContainerDatabaseLocationProvider implements DatabaseLocationProvider {
    @Override
    public Path getDatabasePath() {
        try {
            Path dataDirectory = Path.of("/data");

            Files.createDirectories(dataDirectory);

            return dataDirectory.resolve("mastermind.db");
            //TODO: Think about exception handling logic!
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
