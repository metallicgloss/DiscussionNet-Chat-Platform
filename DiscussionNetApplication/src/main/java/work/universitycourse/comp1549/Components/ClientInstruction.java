package work.universitycourse.comp1549.Components;

import java.util.zip.DataFormatException;

/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 *
 * 
 * ===================================================
 * -                    Contents                     -
 * ===================================================
 * 
 *           Instruction Processing Functions
 *                     
 *           Instruction Validation Functions
 *                          
 *          Validate Instruction Data Components
 *          
 *                  Custom Exceptions
 *          
 *                         > Instruction Not Exist Exception Class
 *          
 *                         > Instruction Format Exception Class
 *            
 *                         > Data Format Exception Class
 * 
 */

public class ClientInstruction {
    
    // NOTE // XXX // IMPORTANT
    // ===================================================
    // -     READ THIS BEFORE ADDING NEW INSTRUCTIONS    -
    // ===================================================
    // Follow these steps when adding a new instruction
    // -> Never sent and instruction type to 0 
    // -> Update the value of 'HIGHEST_INSTRUCTION_CODE' to be the value of the highest instruction code
    // -> Update method 'checkDataFormatValid' to validate instruction's data format (look at section: Instruction Validation Functions)
    // -> Add function to handle instructions (Look at section: Instruction Processing Functions)
    // -> Implement function to handle instruction on server / client side

    private static final int HIGHEST_INSTRUCTION_CODE = 14;
    public static final int SEND_MESSAGE_INSTRUCTION_TYPE = 1;
    public static final int BECOME_COORDINATOR_INSTRUCTION_TYPE = 2;
    public static final int REVOKE_COORDINATOR_INSTRUCTION_TYPE = 3;
    public static final int ESTABLISH_CONNECTION_INSTRUCTION_TYPE = 4;
    public static final int REVIEW_JOIN_REQUEST_INSTRUCTION_TYPE = 5;
    public static final int REJECT_JOIN_REQUEST_INSTRUCTION_TYPE = 6;
    public static final int ACCEPT_CLIENT_CONNECTION_INSTRUCTION_TYPE = 7;
    public static final int UPDATE_CLIENT_INFOS_SERVER_CACHE_INSTRUCTION_TYPE = 8;
    public static final int ADD_CLIENT_INFO_TO_LOCAL_LIST_INSTRUCTION_TYPE = 9;
    public static final int NOTIFY_CLIENT_DISCONNECTED_INSTRUCTION_TYPE = 10;
    public static final int CLIENT_DISCONNECTED_INSTRUCTION_TYPE = 11;
    public static final int GET_UPDATED_CLIENT_INFO_LIST_INSTRUCTION_TYPE = 12;
    public static final int CLIENT_ACCEPTED_INSTRUCTION_TYPE = 13;
    public static final int SET_LOCAL_CLIENT_INFO_LIST_INSTRUCTION_TYPE = 14;
    
    public String data;
    public int instructionType;

    // Used to convert from an instruction string to an instruction object
    public ClientInstruction(String instructionString) throws InstructionNotExistException, InstructionFormatException, DataFormatException {

        // Seperate instruction string into its components <INSTRUCTION_TYPE>, <DATA>
        String[] instructionComponenets = instructionString.split("<SEPERATOR>");
        this.instructionType = ClientInstruction.convertStringToInt(instructionComponenets[0]);

        // Validate values
        ClientInstruction.checkInstructionTypeExist(this.instructionType);

        if (instructionComponenets.length != 2) {
            throw new InstructionFormatException();
        } else {
            this.data = instructionComponenets[1];
            ClientInstruction.checkDataFormatValid(this.instructionType, this.data);
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
    // -        Instruction Processing Functions         -
    // ===================================================

        // Convert instruction objects properties into a string
        public String convertInstructionToString() {
            return Integer.toString(this.instructionType) + "<SEPERATOR>" + this.data;
        }

        // Creates a custom 'Send Message' instruction string
        public static String createSendMessageInstructionString(String receiver, String message) {
            return Integer.toString(ClientInstruction.SEND_MESSAGE_INSTRUCTION_TYPE) + "<SEPERATOR>" + receiver + "::" + message;
        }

        // Create a custom 'Become Coordinator' instruction string
        public static String createBecomeCoordinatorInstructionString() {
            return Integer.toString(ClientInstruction.BECOME_COORDINATOR_INSTRUCTION_TYPE) + "<SEPERATOR>BECOME COORDINATOR";
        }

        // Create a custom 'Revoke Coordinator' instruction string
        public static String createRevokeCoordinatorInstructionString() {
            return Integer.toString(ClientInstruction.REVOKE_COORDINATOR_INSTRUCTION_TYPE) + "<SEPERATOR>REVOKE COORDINATOR";
        }

        // Create a custom 'Establish Connection' instruction string
        public static String createEstablishConnectionInstructionString(String clientID) {
            return Integer.toString(ClientInstruction.ESTABLISH_CONNECTION_INSTRUCTION_TYPE) + "<SEPERATOR>" + clientID;
        }

        // Create a custom 'Review Join Request' instruction string
        public static String createReviewJoinRequestInstructionString(String tempID, String clientID, String clientIP, int clientPort) {
            return Integer.toString(ClientInstruction.REVIEW_JOIN_REQUEST_INSTRUCTION_TYPE) + "<SEPERATOR>" + tempID + "::" + clientID + "::" + clientIP + "::" + Integer.toString(clientPort);
        }

        // Create a custom 'Reject Join Request' instruction string
        public static String createRejectJoinRequestInstructionString(String tempID) {
            return Integer.toString(ClientInstruction.REJECT_JOIN_REQUEST_INSTRUCTION_TYPE) + "<SEPERATOR>" + tempID;
        }

        // Create a custom 'Accept Client Connection' instruction string
        public static String createAcceptClientConnectionInstructionString(String tempID, String clientID) {
            return Integer.toString(ClientInstruction.ACCEPT_CLIENT_CONNECTION_INSTRUCTION_TYPE) + "<SEPERATOR>" + tempID + "::" + clientID;
        }

        // Create a custom 'Update Client Info Server Cache' instruction string
        public static String createUpdateClientInfosServerCacheInstructionString(String clientInfosListString) {
            return Integer.toString(ClientInstruction.UPDATE_CLIENT_INFOS_SERVER_CACHE_INSTRUCTION_TYPE) + "<SEPERATOR>" + clientInfosListString;
        }

        // Create a custom 'Add Client Info To Local List' instruction string
        public static String createAddClientInfoToLocalListInstructionString(String clientID, String clientIP, int clientPort) {
            return Integer.toString(ClientInstruction.ADD_CLIENT_INFO_TO_LOCAL_LIST_INSTRUCTION_TYPE) + "<SEPERATOR>" + clientID + "::" + clientIP + "::" + Integer.toString(clientPort);
        }

        // Create a custom 'Notify Client Has Disconnected' instruction string
        public static String createNotifyClientDisconnectedInstructionString(String clientID) {
            return Integer.toString(ClientInstruction.NOTIFY_CLIENT_DISCONNECTED_INSTRUCTION_TYPE) + "<SEPERATOR>" + clientID;
        }

        // Create a custom 'Client Disconnected' instruction string
        public static String createClientDisconnectedInstructionString(String clientID) {
            return Integer.toString(ClientInstruction.CLIENT_DISCONNECTED_INSTRUCTION_TYPE) + "<SEPERATOR>" + clientID;
        }

        // Create a custom 'Get Updated Client Info List' instruction string
        public static String createGetUpdatedClientInfoListInstructionString(String senderID) {
            return Integer.toString(ClientInstruction.GET_UPDATED_CLIENT_INFO_LIST_INSTRUCTION_TYPE) + "<SEPERATOR>" + senderID;
        }

        // Create a custom 'Client Accepted' instruction string
        public static String createClientAcceptedInstructionString(String coordinatorID) {
            return Integer.toString(ClientInstruction.CLIENT_ACCEPTED_INSTRUCTION_TYPE) + "<SEPERATOR>" + coordinatorID;
        }

        // Create a custom 'Set Local Client Info List' instruction string
        public static String createSetLocalClientInfoListString(String allClientInfoString) {
            return Integer.toString(ClientInstruction.SET_LOCAL_CLIENT_INFO_LIST_INSTRUCTION_TYPE) + "<SEPERATOR>" + allClientInfoString;
        }


    // ===================================================
    // -          Instruction Validation Functions       -
    // ===================================================

        // Checks the instruction code provided is a genuine
        private static void checkInstructionTypeExist(int instructionType) throws InstructionNotExistException {

            if (instructionType <= 0 || instructionType > ClientInstruction.HIGHEST_INSTRUCTION_CODE) {
                throw new InstructionNotExistException(instructionType);
            }

        }

        // Checks the data provided is in the valid form the instruction code provided
        private static void checkDataFormatValid(int instructionType, String data) throws DataFormatException, InstructionNotExistException {

            switch (instructionType) {
                case ClientInstruction.SEND_MESSAGE_INSTRUCTION_TYPE:

                    // FORMAT: RECEIVER::MESSAGE
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
                
                case ClientInstruction.ESTABLISH_CONNECTION_INSTRUCTION_TYPE:

                    // FORMAT: CLIENT_ID
                    ClientInstruction.validateDataForEstablishConnectionInstruction(data);
                    break;
                
                case ClientInstruction.REVIEW_JOIN_REQUEST_INSTRUCTION_TYPE:

                    // FORMAT: TEMP_ID::CLIENT_ID::CLIENT_IP::CLIENT_PORT
                    ClientInstruction.validateDataForReviewJoinRequestInstruction(data);
                    break;
                
                case ClientInstruction.REJECT_JOIN_REQUEST_INSTRUCTION_TYPE:

                    // FORMAT: TEMP_ID
                    ClientInstruction.validateDataForRejectJoinRequestInstruction(data);
                    break;
                
                case ClientInstruction.ACCEPT_CLIENT_CONNECTION_INSTRUCTION_TYPE:

                    // FORMAT: TEMP_ID::CLIENT_ID
                    ClientInstruction.validateDataForAcceptClientConnectionInstruction(data);
                    break;
                
                case ClientInstruction.UPDATE_CLIENT_INFOS_SERVER_CACHE_INSTRUCTION_TYPE:

                    // FORMAT: CLIENT_INFO_LIST_STRING
                    ClientInstruction.validateDataForUpdateClientInforServerCacheInstruction(data);
                    break;
                
                case ClientInstruction.ADD_CLIENT_INFO_TO_LOCAL_LIST_INSTRUCTION_TYPE:

                    // FORMAT: CLIENT_ID::CLIENT_IP::CLIENT_PORT
                    ClientInstruction.validateDataForAddClientInfoToLocalList(data);
                    break;
                
                case ClientInstruction.NOTIFY_CLIENT_DISCONNECTED_INSTRUCTION_TYPE:

                    // FORMAT: CLIENT_ID
                    ClientInstruction.validateDataForNotifyClientDisonnected(data);
                    break;
                
                case ClientInstruction.CLIENT_DISCONNECTED_INSTRUCTION_TYPE:

                    // FORMAT: CLIENT DISCONNECTED
                    ClientInstruction.validateDataForClientDisconnected(data);
                    break;
                
                case ClientInstruction.GET_UPDATED_CLIENT_INFO_LIST_INSTRUCTION_TYPE:

                    // FORMAT: SENDER_ID
                    ClientInstruction.validateDataForGetUpdatedClientInfoList(data);
                    break;
                
                case ClientInstruction.CLIENT_ACCEPTED_INSTRUCTION_TYPE:

                    // FORMAT: COORDINATOR_ID
                    ClientInstruction.validateaDataFromAcceptedClient(data);
                    break;
                
                case ClientInstruction.SET_LOCAL_CLIENT_INFO_LIST_INSTRUCTION_TYPE:

                    // FORMAT: ALL_CLIENT_INFO_LIST_STRING
                    ClientInstruction.validateDataForSetLocalClientInfoList(data);
                    break;

                default:
                    throw new InstructionNotExistException(instructionType);
            }

        }
    
    // ===================================================
    // -        Validate Instruction Data Components     -
    // ===================================================

        // Checks data provided is in a valid form for 'Send Message' instruction type
        private static void validateDataForMessageInstruction(String data) throws DataFormatException {

            String[] dataComponents = data.split("::");

            if (dataComponents.length != 2) {
                throw new DataFormatException("RECEIVER::MESSAGE");
            }

        }

        // Checks data provided is in a valid form for 'Become Coordinator' instruction type
        private static void validateDataForBecomeCoordinatorInstruction(String data) throws DataFormatException {

            String[] dataComponents = data.split("::");

            if (! dataComponents[0].equals("BECOME COORDINATOR") || dataComponents.length != 1) {
                throw new DataFormatException("BECOME COORDINATOR");
            }

        }

        // Checks data provided is in a valid form for 'Revoke Coordinator' instruction type
        private static void validateDataForRevokeCoordinatorInstruction(String data) throws DataFormatException {

            String[] dataComponents = data.split("::");

            if (! dataComponents[0].equals("REVOKE COORDINATOR") || dataComponents.length != 1) {
                throw new DataFormatException("REVOKE COORDINATOR");
            }

        }

        // Checks data provided is in a valid form for a 'Establish Connection' instruction type
        private static void validateDataForEstablishConnectionInstruction(String data) throws DataFormatException {

            String[] dataComponents = data.split("::");

            if (dataComponents.length != 1) {
                throw new DataFormatException("CLIENT_ID");
            }

        }

        // Checks data provided is in a valid form for a 'Review Join Request' instruction type
        private static void validateDataForReviewJoinRequestInstruction(String data) throws DataFormatException {

            String[] dataComponents = data.split("::");

            if (dataComponents.length != 4) {
                throw new DataFormatException("TEMP_ID::CLIENT_ID::CLIENT_IP::CLIENT_PORT");
            }

        }

        // Checks data provided is in a valid form for a 'Reject Join Request' instruction type
        private static void validateDataForRejectJoinRequestInstruction(String data) throws DataFormatException {

            String[] dataComponents = data.split("::");

            if (dataComponents.length != 1) {
                throw new DataFormatException("TEMP_ID");
            }

        }

        // Checks data provided is in a valid form for a 'Accept Client Connection' instruction type
        private static void validateDataForAcceptClientConnectionInstruction(String data) throws DataFormatException {

            String[] dataComponents = data.split("::");

            if (dataComponents.length != 2) {
                throw new DataFormatException("TEMP_ID::CLIENT_ID");
            }

        }
        
        // Checks data provided is in a valid form for a 'Updata Client Info Server Cache' instruction type
        private static void validateDataForUpdateClientInforServerCacheInstruction(String data) throws DataFormatException {

            String[] dataComponents = data.split("::");
            
            if (dataComponents.length != 1) {
                throw new DataFormatException("CLIENT_INFO_LIST_STRING");
            }

        }

        // Checks data provided is in a valid form for a 'Add Client Info To Local List' instruction type
        private static void validateDataForAddClientInfoToLocalList(String data) throws DataFormatException {

            String[] dataComponents = data.split("::");

            if (dataComponents.length != 3) {
                throw new DataFormatException("CLIENT_ID::CLIENT_IP::CLIENT_PORT");
            }

        }

        // Checks data provided is in a valid form for a 'Notify Client Has Disconnected' instruction type
        private static void validateDataForNotifyClientDisonnected(String data) throws DataFormatException {

            String[] dataComponents = data.split("::");

            if (dataComponents.length != 1) {
                throw new DataFormatException("CLIENT_ID");
            }

        }

        // Checks data provided is in a valid form for a 'Client Disconnected' instruction type
        private static void validateDataForClientDisconnected(String data) throws DataFormatException {

            String[] dataComponents = data.split("::");

            if (dataComponents.length != 1) {
                throw new DataFormatException("CLIENT_ID");
            }

        }

        // Checks data provided is in a valid form for a 'Get Updated Client Info List' instruction type
        private static void validateDataForGetUpdatedClientInfoList(String data) throws DataFormatException {

            String[] dataComponents = data.split("::");

            if (dataComponents.length != 1) {
                throw new DataFormatException("SENDER_ID");
            }

        }

        // Checks data provided is in a valid form for a 'Accept Client' instruction type
        private static void validateaDataFromAcceptedClient(String data) throws DataFormatException {

            String[] dataComponents = data.split("::");

            if (dataComponents.length != 1) {
                throw new DataFormatException("COORDINATOR_ID");
            }

        }

        // Checks data provided is in a valid form for a 'Set Local Client Info List' instruction type
        private static void validateDataForSetLocalClientInfoList(String data) throws DataFormatException {

            String[] dataComponents = data.split("::");

            if (dataComponents.length != 1) {
                throw new DataFormatException("ALL_CLIENT_INFO_LIST_STRING");
            }

        }
    
    // ===================================================
    // -               Custom Exceptions                -
    // ===================================================
    
        // ===================================================
        // -      Instruction Not Exist Exception Class      -
        // ===================================================

            /**
             * Thrown when attempting to create an instruction using an instruction code that does not exist.
             */

            public static final class InstructionNotExistException extends Exception {

                public InstructionNotExistException(int instructionType) {
                    super("Instruction Code '" + Integer.toString(instructionType) + "' Does Not Exist.");
                }

            }
        
        // ===================================================
        // -       Instruction Format Exception Class        -
        // ===================================================

            /**
             * Thrown when an instruction object is not formatted correctly
             */

            public static final class InstructionFormatException extends Exception {
        
                public InstructionFormatException() {
                    super("Instruction Format Should Be '<INSTRUCTION CODE><SEPERATOR><DATA>'");
                }
            
            }
        
        // ===================================================
        // -           Data Format Exception Class           -
        // ===================================================

            /**
             * Thrown when the data component of an instruction is not appropriately formed
             */

            public static final class DataFormatException extends Exception {
        
                public DataFormatException(String expectedFormat) {
                    super("Data Format Should be: " + expectedFormat);
                }
            
            }

}
