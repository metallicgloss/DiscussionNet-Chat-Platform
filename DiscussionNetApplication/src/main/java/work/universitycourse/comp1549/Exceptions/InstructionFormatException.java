package work.universitycourse.comp1549.Exceptions;

public class InstructionFormatException extends Exception {
    
    public InstructionFormatException() {
        super("Instruction Format Should Be '<INSTRUCTION CODE>::<DATA>' excluding '<' and '>'");
    }

}
