package work.universitycourse.comp1549.Exceptions;

/**
 * Throw when the data component of an instruction is not appropriately formed
 */
public class DataFormatInvalidException extends Exception {
    
    public DataFormatInvalidException(String expectedFormat) {
        super("Data Format Should be: " + expectedFormat);
    }

}
