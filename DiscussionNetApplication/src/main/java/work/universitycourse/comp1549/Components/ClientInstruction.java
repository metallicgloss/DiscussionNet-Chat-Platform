package work.universitycourse.comp1549.Components;

import work.universitycourse.comp1549.Exceptions.InstructionNotExistException;
import work.universitycourse.comp1549.Exceptions.InstructionFormatException; 
import work.universitycourse.comp1549.Exceptions.DataFormatInvalidException; 

public class ClientInstruction {
    
    // NOTE // XXX // IMPORTANT
    // -> Never sent and instruction code to 0 
    // -> Update the value of 'HIGHEST_INSTRUCTION_CODE' to be the value of the highest instruction code
    // -> Update method 'checkDataStringValid' to handle instructions data type
    private static final int HIGHEST_INSTRUCTION_CODE = 1;
    public static final int SEND_MESSAGE_INSTRUCTION_TYPE = 1;
    
    public String data;
    public int instructionType;


    // Used to create an instruction
    public ClientInstruction(int instructionType, String data) throws InstructionNotExistException, DataFormatInvalidException {
        // Check instruction code exists
        ClientInstruction.checkInstructionCodeExist(instructionType);
        ClientInstruction.checkDataStringValid(instructionType, data);

        this.instructionType = instructionType;
        this.data = data;
    }
    

    // Used to convert from an instruction string to an instruction object
    public ClientInstruction(String instructionString) throws InstructionNotExistException, InstructionFormatException, DataFormatInvalidException {
        // Seperate instruction string into its components <INSTRUCTION_TYPE>, <DATA>
        String[] instructionComponenets = instructionString.split("::");
        this.instructionType = ClientInstruction.convertStringToInt(instructionComponenets[0]);

        // Validate values
        ClientInstruction.checkInstructionCodeExist(this.instructionType);

        if (instructionComponenets.length != 2) {
            throw new InstructionFormatException();
        } else {
            this.data = instructionComponenets[1];
            ClientInstruction.checkDataStringValid(this.instructionType, this.data);
        }
    }

    // Convert instruction objects properties into a string
    public String convertInstructionToString() {
        return Integer.toString(this.instructionType) + "::" + this.data;
    }

    // Creates a custom 'Send Message' instruction string
    public static String createSendMessageInstructionString(String receiver, String message) {
        return Integer.toString(ClientInstruction.SEND_MESSAGE_INSTRUCTION_TYPE) + "::" + receiver + "<SEPERATOR>" + message;
    }

    // Converts String to Int
    private static int convertStringToInt(String text) {
        int temp = 0;
        try {
            temp = Integer.parseInt(text);
        } catch (NumberFormatException e) {}
        return temp;
    }

    // Checks the instruction code provided is a genuine
    private static void checkInstructionCodeExist(int instructionCode) throws InstructionNotExistException {
        if (instructionCode <= 0 || instructionCode > ClientInstruction.HIGHEST_INSTRUCTION_CODE) {
            throw new InstructionNotExistException(instructionCode);
        }
    }

    // Checks the data provided is in the valid form the instruction code provided
    private static void checkDataStringValid(int instructionCode, String data) throws DataFormatInvalidException, InstructionNotExistException {
        switch (instructionCode) {
            case ClientInstruction.SEND_MESSAGE_INSTRUCTION_TYPE:
                // FORMAT RECIEVER<SEPERATOR>MESSAGE
                ClientInstruction.validateDataForMessageInstruction(data);
                break;
            default:
                throw new InstructionNotExistException(instructionCode);
        }
    }

    // Checks data provided is in a valid form for 'Send Message' instruction type
    private static void validateDataForMessageInstruction(String data) throws DataFormatInvalidException {
        String[] dataComponents = data.split("<SEPERATOR>");
        if (dataComponents.length != 2) {
            throw new DataFormatInvalidException("RECIEVER<SEPERATOR>MESSAGE");
        }
    }

}
