package work.universitycourse.comp1549.Exceptions;

/**
 * Throw when a client tries to update their local client list with a client that already exists
 */

public class ClientIDAlreadyInListException extends Exception {

    public ClientIDAlreadyInListException(String expectedFormat) {
        super("Data Format Should be: " + expectedFormat);
    }

}
