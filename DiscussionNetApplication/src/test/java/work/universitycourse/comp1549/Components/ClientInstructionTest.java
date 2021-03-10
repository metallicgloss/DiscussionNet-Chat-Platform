/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package work.universitycourse.comp1549.Components;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Gabriel
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
     */
    @org.junit.jupiter.api.Test
    public void testConvertInstructionToString() {
        System.out.println("convertInstructionToString");
        ClientInstruction instance = null;
        String expResult = "";
        String result = instance.convertInstructionToString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createSendMessageInstructionString method, of class ClientInstruction.
     */
    @org.junit.jupiter.api.Test
    public void testCreateSendMessageInstructionString() {
        System.out.println("createSendMessageInstructionString");
        String receiver = "";
        String message = "";
        String expResult = "";
        String result = ClientInstruction.createSendMessageInstructionString(receiver, message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createBecomeCoordinatorInstructionString method, of class ClientInstruction.
     */
    @org.junit.jupiter.api.Test
    public void testCreateBecomeCoordinatorInstructionString() {
        System.out.println("createBecomeCoordinatorInstructionString");
        String expResult = "";
        String result = ClientInstruction.createBecomeCoordinatorInstructionString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRevokeCoordinatorInstructionString method, of class ClientInstruction.
     */
    @org.junit.jupiter.api.Test
    public void testCreateRevokeCoordinatorInstructionString() {
        System.out.println("createRevokeCoordinatorInstructionString");
        String expResult = "";
        String result = ClientInstruction.createRevokeCoordinatorInstructionString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createEstablishConnectionInstructionString method, of class ClientInstruction.
     */
    @org.junit.jupiter.api.Test
    public void testCreateEstablishConnectionInstructionString() {
        System.out.println("createEstablishConnectionInstructionString");
        String clientID = "";
        String expResult = "";
        String result = ClientInstruction.createEstablishConnectionInstructionString(clientID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createReviewJoinRequestInstructionString method, of class ClientInstruction.
     */
    @org.junit.jupiter.api.Test
    public void testCreateReviewJoinRequestInstructionString() {
        System.out.println("createReviewJoinRequestInstructionString");
        String tempID = "";
        String clientID = "";
        String clientIP = "";
        int clientPort = 0;
        String expResult = "";
        String result = ClientInstruction.createReviewJoinRequestInstructionString(tempID, clientID, clientIP, clientPort);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
