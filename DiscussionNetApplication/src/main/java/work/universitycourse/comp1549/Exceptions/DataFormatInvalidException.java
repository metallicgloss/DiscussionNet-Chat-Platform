package work.universitycourse.comp1549.Exceptions;

public class DataFormatInvalidException extends Exception {
    
    public DataFormatInvalidException(String expectedFormat) {
        super("Data Format Should be: " + expectedFormat);
    }

}
