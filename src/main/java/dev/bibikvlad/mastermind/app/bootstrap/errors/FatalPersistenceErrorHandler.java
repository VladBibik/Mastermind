package dev.bibikvlad.mastermind.app.bootstrap.errors;

import dev.bibikvlad.mastermind.app.printer.Printer;
import dev.bibikvlad.mastermind.exceptions.PersistenceException;

public class FatalPersistenceErrorHandler {

    private final Printer printer;

    public FatalPersistenceErrorHandler(Printer printer) {
        this.printer = printer;
    }

    public void handle(PersistenceException exception) {
        String EXCEPTION_MESSAGE = "Problem with the database occurred. " +
                "Please check your environment and try again later";

        printer.printMessage(EXCEPTION_MESSAGE + "\n" + exception.getMessage());

        System.exit(1);
    }
}
