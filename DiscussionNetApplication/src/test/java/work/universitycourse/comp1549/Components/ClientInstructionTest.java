
package work.universitycourse.comp1549.Components;

import work.universitycourse.comp1549.Components.ClientInstruction.DataFormatException;
import work.universitycourse.comp1549.Components.ClientInstruction.InstructionFormatException;
import work.universitycourse.comp1549.Components.ClientInstruction.InstructionNotExistException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 */

public class ClientInstructionTest {
    
    public ClientInstructionTest() {
    }

    @org.junit.jupiter.api.BeforeAll
    public static void setUpClass() throws Exception {
    }

    @org.junit.jupiter.api.AfterAll
    public static void tearDownClass() throws Exception {
    }

    @org.junit.jupiter.api.BeforeEach
    public void setUp() throws Exception {
    }

    @org.junit.jupiter.api.AfterEach
    public void tearDown() throws Exception {
    }
    
    

    /**
     * Test of convertInstructionToString method, of class ClientInstruction.
     * DONE
     */
    @org.junit.jupiter.api.Test
    public void testConvertInstructionToString() {
        System.out.println("convertInstructionToString");
        ClientInstruction instance;
        String expResult = "<MESSAGE>,<TEST_MESSAGE>";
        String test = "message<separator>test_message";
        try {
            instance = new ClientInstruction(test);
        } catch (InstructionNotExistException | InstructionFormatException | DataFormatException e) {
            e.printStackTrace();
        }
        
        // NOTE: Needs fixing, test not completed.
        //String result = instance.convertInstructionToString();
        //assertEquals(expResult, result);
        
    }

    /**
     * Test of createSendMessageInstructionString method, of class ClientInstruction.
     * DONE
     */
    @org.junit.jupiter.api.Test
    public void testCreateSendMessageInstructionString() {
        System.out.println("createSendMessageInstructionString");
        String receiver = "COORDINATOR";
        String message = "testmessage";
        String expResult = Integer.toString(ClientInstruction.SEND_MESSAGE_INSTRUCTION_TYPE) + "<SEPERATOR>" + receiver + "::" + message;
        
        // NOTE: Needs fixing, test not completed.
        //String result = ClientInstruction.createSendMessageInstructionString(receiver, message);
        //assertEquals(expResult, result);
    }

    /**
     * Test of createBecomeCoordinatorInstructionString method, of class ClientInstruction.
     * DONE
     */
    @org.junit.jupiter.api.Test
    public void testCreateBecomeCoordinatorInstructionString() {
        System.out.println("createBecomeCoordinatorInstructionString");
        String expResult = Integer.toString(ClientInstruction.BECOME_COORDINATOR_INSTRUCTION_TYPE) + "<SEPERATOR>BECOME COORDINATOR";
        String result = ClientInstruction.createBecomeCoordinatorInstructionString();
        assertEquals(expResult, result);
    }

    /**
     * Test of createRevokeCoordinatorInstructionString method, of class ClientInstruction.
     * DONE
     */
    @org.junit.jupiter.api.Test
    public void testCreateRevokeCoordinatorInstructionString() {
        System.out.println("createRevokeCoordinatorInstructionString");
        String expResult = Integer.toString(ClientInstruction.REVOKE_COORDINATOR_INSTRUCTION_TYPE) + "<SEPERATOR>REVOKE COORDINATOR";
        String result = ClientInstruction.createRevokeCoordinatorInstructionString();
        assertEquals(expResult, result);
    }

    /**
     * Test of createEstablishConnectionInstructionString method, of class ClientInstruction.
     * DONE
     */
    @org.junit.jupiter.api.Test
    public void testCreateEstablishConnectionInstructionString() {
        System.out.println("createEstablishConnectionInstructionString");
        String clientID = "ID";
        String expResult = Integer.toString(ClientInstruction.ESTABLISH_CONNECTION_INSTRUCTION_TYPE) + "<SEPERATOR>" + clientID;
        String result = ClientInstruction.createEstablishConnectionInstructionString(clientID);
        assertEquals(expResult, result);
    }

    /**
     * Test of createReviewJoinRequestInstructionString method, of class ClientInstruction.
     * DONE
     */
    @org.junit.jupiter.api.Test
    public void testCreateReviewJoinRequestInstructionString() {
        System.out.println("createReviewJoinRequestInstructionString");
        String tempID = "123";
        String clientID = "12";
        String clientIP = "192.168.1.0";
        int clientPort = 0;
        String expResult = Integer.toString(ClientInstruction.REVIEW_JOIN_REQUEST_INSTRUCTION_TYPE) + "<SEPERATOR>" + tempID + "::" + clientID + "::" + clientIP + "::" + Integer.toString(clientPort);
        String result = ClientInstruction.createReviewJoinRequestInstructionString(tempID, clientID, clientIP, clientPort);
        assertEquals(expResult, result);
    }

    
    /**
     * Test of createRejectJoinRequestInstructionString method, of class ClientInstruction.
     */
    @org.junit.jupiter.api.Test
    public void testCreateRejectJoinRequestInstructionString() {
        System.out.println("createRejectJoinRequestInstructionString");
        String tempID = "";
        String expResult = "";
        String result = ClientInstruction.createRejectJoinRequestInstructionString(tempID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createAcceptClientConnectionInstructionString method, of class ClientInstruction.
     */
    @org.junit.jupiter.api.Test
    public void testCreateAcceptClientConnectionInstructionString() {
        System.out.println("createAcceptClientConnectionInstructionString");
        String tempID = "";
        String clientID = "";
        String expResult = "";
        String result = ClientInstruction.createAcceptClientConnectionInstructionString(tempID, clientID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createUpdateClientInfosServerCacheInstructionString method, of class ClientInstruction.
     */
    @org.junit.jupiter.api.Test
    public void testCreateUpdateClientInfosServerCacheInstructionString() {
        System.out.println("createUpdateClientInfosServerCacheInstructionString");
        String clientInfosListString = "";
        String expResult = "";
        String result = ClientInstruction.createUpdateClientInfosServerCacheInstructionString(clientInfosListString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createAddClientInfoToLocalListInstructionString method, of class ClientInstruction.
     */
    @org.junit.jupiter.api.Test
    public void testCreateAddClientInfoToLocalListInstructionString() {
        System.out.println("createAddClientInfoToLocalListInstructionString");
        String clientID = "";
        String clientIP = "";
        int clientPort = 0;
        String expResult = "";
        String result = ClientInstruction.createAddClientInfoToLocalListInstructionString(clientID, clientIP, clientPort);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createNotifyClientDisconnectedInstructionString method, of class ClientInstruction.
     */
    @org.junit.jupiter.api.Test
    public void testCreateNotifyClientDisconnectedInstructionString() {
        System.out.println("createNotifyClientDisconnectedInstructionString");
        String clientID = "";
        String expResult = "";
        String result = ClientInstruction.createNotifyClientDisconnectedInstructionString(clientID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createClientDisconnectedInstructionString method, of class ClientInstruction.
     */
    @org.junit.jupiter.api.Test
    public void testCreateClientDisconnectedInstructionString() {
        System.out.println("createClientDisconnectedInstructionString");
        String clientID = "";
        String expResult = "";
        String result = ClientInstruction.createClientDisconnectedInstructionString(clientID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createGetUpdatedClientInfoListInstructionString method, of class ClientInstruction.
     */
    @org.junit.jupiter.api.Test
    public void testCreateGetUpdatedClientInfoListInstructionString() {
        System.out.println("createGetUpdatedClientInfoListInstructionString");
        String senderID = "";
        String expResult = "";
        String result = ClientInstruction.createGetUpdatedClientInfoListInstructionString(senderID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createClientAcceptedInstructionString method, of class ClientInstruction.
     */
    @org.junit.jupiter.api.Test
    public void testCreateClientAcceptedInstructionString() {
        System.out.println("createClientAcceptedInstructionString");
        String coordinatorID = "";
        String expResult = "";
        String result = ClientInstruction.createClientAcceptedInstructionString(coordinatorID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createSetLocalClientInfoListString method, of class ClientInstruction.
     */
    @org.junit.jupiter.api.Test
    public void testCreateSetLocalClientInfoListString() {
        System.out.println("createSetLocalClientInfoListString");
        String allClientInfoString = "";
        String expResult = "";
        String result = ClientInstruction.createSetLocalClientInfoListString(allClientInfoString);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
