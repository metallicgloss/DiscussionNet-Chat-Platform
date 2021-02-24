package work.universitycourse.comp1549.Components;

import work.universitycourse.comp1549.Exceptions.InstructionNotExistException;
import work.universitycourse.comp1549.Exceptions.InstructionFormatException; 
import work.universitycourse.comp1549.Exceptions.DataFormatInvalidException; 



public class ClientInstruction {
    
    // NOTE // XXX // IMPORTANT
    // ===================================================
    // -     READ THIS BEFORE ADDING NEW INSTRUCTIONS    -
    // ===================================================
    // Follow these steps when adding a new instruction
    // -> Never sent and instruction type to 0 
    // -> Update the value of 'HIGHEST_INSTRUCTION_CODE' to be the value of the highest instruction code
    // -> Update method 'checkDataStringValid' to validate instruction's data format (look at section: Instruction Validation Functions)
    // -> Add function to handle instructions (Look at section: Instruction Handler Functions)
    // -> Implement function to handle instruction on server / client side

    private static final int HIGHEST_INSTRUCTION_CODE = 5;
    public static final int SEND_MESSAGE_INSTRUCTION_TYPE = 1;
    public static final int BECOME_COORDINATOR_INSTRUCTION_TYPE = 2;
    public static final int REVOKE_COORDINATOR_INSTRUCTION_TYPE = 3;
    public static final int REQUEST_CLIENT_LIST_INSTRUCTION_TYPE = 4; // TODO Trace this and may need changing to allow coordinator to treat this differently from other clients
    public static final int ADD_CLIENT_TO_LIST_INSTRUCTION_TYPE = 5;
    
    public String data;
    public int instructionType;

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

    // Converts String to Int
    private static int convertStringToInt(String text) {

        int temp = 0;
        try {
            temp = Integer.parseInt(text);
        } catch (NumberFormatException e) {}
        return temp;

    }

    // ===================================================
    // -          Instruction Handler Functions          -
    // ===================================================

    // Convert instruction objects properties into a string
    public String convertInstructionToString() {
        return Integer.toString(this.instructionType) + "::" + this.data;
    }

    // Creates a custom 'Send Message' instruction string
    public static String createSendMessageInstructionString(String receiver, String message) {
        return Integer.toString(ClientInstruction.SEND_MESSAGE_INSTRUCTION_TYPE) + "::" + receiver + "<SEPERATOR>" + message;
    }

    // Create a custom 'Become Coordinator' instruction string
    public static String createBecomeCoordinatorInstructionString() {
        return Integer.toString(ClientInstruction.BECOME_COORDINATOR_INSTRUCTION_TYPE) + "::BECOME COORDINATOR";
    }

    // Create a custom 'Revoke Coordinator' instruction string
    public static String revokeCoordinatorInstructionString() {
        return Integer.toString(ClientInstruction.REVOKE_COORDINATOR_INSTRUCTION_TYPE) + "::REVOKE COORDINATOR";
    }

    // Create a custom 'Request Clients List' instruction string
    public static String requestClientListInstructionString() {
        return Integer.toString(ClientInstruction.REVOKE_COORDINATOR_INSTRUCTION_TYPE) + "::REQUEST CLIENT LIST";
    }

    // ===================================================
    // -          Instruction Validation Functions       -
    // ===================================================

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

                // FORMAT: RECEIVER<SEPERATOR>MESSAGE
                ClientInstruction.validateDataForMessageInstruction(data);
                break;

            case ClientInstruction.BECOME_COORDINATOR_INSTRUCTION_TYPE:

                // FORMAT: BECOME COORDINATOR
                ClientInstruction.validateDataForBecomeCoordinatorInstruction(data);
                break;

            case ClientInstruction.REVOKE_COORDINATOR_INSTRUCTION_TYPE:

                // FORMAT: REVOKE COORDINATOR
                ClientInstruction.validateDataForRevokeCoordinatorInstruction(data);
                break;
            
            case ClientInstruction.REQUEST_CLIENT_LIST_INSTRUCTION_TYPE:

                // FORMAT: REQUEST CLIENT LIST
                ClientInstruction.validateRequestClientListInstruction(data);
                break;
            
            case ClientInstruction.ADD_CLIENT_TO_LIST_INSTRUCTION_TYPE:

                // FORMAT: ADD CLIENT TO LIST
                ClientInstruction.validateAddClientToListInstruction(data);
                break;

            default:
                throw new InstructionNotExistException(instructionCode);
        }

    }

    // ===================================================
    // -        Validate Instruction Data Components     -
    // ===================================================

    // Checks data provided is in a valid form for 'Send Message' instruction type
    private static void validateDataForMessageInstruction(String data) throws DataFormatInvalidException {

        String[] dataComponents = data.split("<SEPERATOR>");

        if (dataComponents.length != 2) {
            throw new DataFormatInvalidException("RECEIVER<SEPERATOR>MESSAGE");
        }

    }

    // Checks data provided is in a valid form for 'Become Coordinator' instruction type
    private static void validateDataForBecomeCoordinatorInstruction(String data) throws DataFormatInvalidException {

        String[] dataComponents = data.split("<SEPERATOR>");

        if (! dataComponents[0].equals("BECOME COORDINATOR") && dataComponents.length != 1) {
            throw new DataFormatInvalidException("BECOME COORDINATOR");
        }

    }

    // Checks data provided is in a valid form for 'Revoke Coordinator' instruction type
    private static void validateDataForRevokeCoordinatorInstruction(String data) throws DataFormatInvalidException {

        String[] dataComponents = data.split("<SEPERATOR>");

        if (! dataComponents[0].equals("REVOKE COORDINATOR") && dataComponents.length != 1) {
            throw new DataFormatInvalidException("REVOKE COORDINATOR");
        }

    }

    // Checks data provided is in a valid form for 'Request Client List' instruction type
    private static void validateRequestClientListInstruction(String data) throws DataFormatInvalidException {

        String[] dataComponents = data.split("<SEPERATOR>");

        if (! dataComponents[0].equals("REQUEST CLIENT LIST") && dataComponents.length != 1) {
            throw new DataFormatInvalidException("REQUEST CLIENT LIST");
        }

    }

    // Checks data provided is in a valid form for 'Request Client List' instruction type
    private static void validateAddClientToListInstruction(String data) throws DataFormatInvalidException {

        String[] dataComponents = data.split("<SEPERATOR>"); // FORMAT: <ADD CLIENT TO LIST><SEPERATOR><CLIENT_IP><SEPERATOR><CLIENT_PORT><SEPERATOR><CLIENT_ID>
        // TODO maybe add more checks to ensure they are in correct format

        if (! dataComponents[0].equals("ADD CLIENT TO LIST") && dataComponents.length != 4) {
            throw new DataFormatInvalidException("ADD CLIENT TO LIST");
        }

    }

}
