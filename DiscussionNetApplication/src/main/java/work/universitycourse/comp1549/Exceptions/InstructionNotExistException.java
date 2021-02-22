package work.universitycourse.comp1549.Exceptions;

public class InstructionNotExistException extends Exception {

    public InstructionNotExistException(int instructionCode) {
        super("Instruction Code '" + Integer.toString(instructionCode) + "' Does Not Exist.");
    }

}