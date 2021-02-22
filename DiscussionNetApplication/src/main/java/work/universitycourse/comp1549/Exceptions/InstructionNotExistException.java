package work.universitycourse.comp1549.Exceptions;

/**
 * Throw when attempting to create an instruction using an instruction code that does not exist.
 */

public class InstructionNotExistException extends Exception {

    public InstructionNotExistException(int instructionCode) {
        super("Instruction Code '" + Integer.toString(instructionCode) + "' Does Not Exist.");
    }

}