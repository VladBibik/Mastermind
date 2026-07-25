package dev.bibikvlad.mastermind.app.bootstrap.path.provider;

import dev.bibikvlad.mastermind.exceptions.PersistenceException;

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
        } catch (IOException exception) {
            throw new PersistenceException("Failed to create database directory '/data'.", exception);
        }
    }
}
