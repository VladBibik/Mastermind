package dev.bibikvlad.mastermind.exceptions.handlers;

//TODO: Add printer instead of SOUT!
public class FatalPersistenceErrorHandler {
    private final static String EXCEPTION_MESSAGE = "Problem with the database occurred. " +
            "Please check your environment and try again later";

    public static void handle(Exception exception) {
        System.out.println(EXCEPTION_MESSAGE + "\n" + exception.getMessage());

        System.exit(1);
    }
}
