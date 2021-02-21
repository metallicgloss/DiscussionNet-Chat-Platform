package work.universitycourse.comp1549.Components;

public class ClientInstruction {
    
    public static final int SEND_MESSAGE_INSTRUCTION_TYPE = 1;
    
    public String data;
    public int instructionType;

    // Used to create an instruction
    public ClientInstruction(int instructionType, String data) {
        // TODO check that instructionType is legit else throw same appropriate error message as other constructor
        this.instructionType = instructionType;
        this.data = data;
    }
    

    // Used to convert from an instruction string to an instruction object
    public ClientInstruction(String instructionString) {
        String[] instructionComponenets = instructionString.split("::");
        this.instructionType = ClientInstruction.convertStringToInt(instructionComponenets[0]);
        if (this.instructionType == 0) {
            // TODO throw custom error message stating instruction does not exist
        } else if (instructionComponenets.length != 2) {
            // TODO throw custom error message stating data format is not legit
        } else {
            this.data = instructionComponenets[1];
        }
    }

    public String convertInstructionToString() {
        return Integer.toString(this.instructionType) + "::" + this.data;
    }

    public static String createSendMessageInstructionString(String receiver, String message) {
        return Integer.toString(ClientInstruction.SEND_MESSAGE_INSTRUCTION_TYPE) + "::" + receiver + "<SEPERATOR>" + message;
    }


    private static int convertStringToInt(String text) {
        int temp = 0;
        try {
            temp = Integer.parseInt(text);
        } catch (NumberFormatException e) {}
        return temp;
    }

}
