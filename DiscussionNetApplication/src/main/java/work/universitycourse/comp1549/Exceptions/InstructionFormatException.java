package work.universitycourse.comp1549.Exceptions;

/**
 * Throw when an instruction object is not formed correctly
 */
public class InstructionFormatException extends Exception {
    
    public InstructionFormatException() {
        super("Instruction Format Should Be '<INSTRUCTION CODE>::<DATA>' excluding '<' and '>'");
    }

}
