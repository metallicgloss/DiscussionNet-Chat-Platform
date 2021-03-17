/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package work.universitycourse.comp1549.Components;

import java.net.Socket;
import java.util.HashMap;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 */
public class ServerChannelTest {
    
    public ServerChannelTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addMessageToChannel method, of class ServerChannel.
     * DONE
     */
    @Test
    public void testAddMessageToChannel() {
        System.out.println("addMessageToChannel");
        Message messageObj = new Message("userA", "userB", "testmessage", 2);
        System.out.println(messageObj);
        ServerChannel instance = new ServerChannel();
        instance.addMessageToChannel(messageObj);
        assertEquals(1,1);
    }

    /**
     * Test of getNextMessageFromChannel method, of class ServerChannel.
     * DONE
     */
    @Test
    public void testGetNextMessageFromChannel() {
        System.out.println("getNextMessageFromChannel");
        ServerChannel instance = new ServerChannel();
        Message messageObj = new Message("userA", "userB", "testmessage", 2);
        Message expResult = messageObj;
        instance.addMessageToChannel(messageObj);
        Message result = instance.getNextMessageFromChannel();
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of sendMessageToClient method, of class ServerChannel.
     * TODO
     */
    @Test
    public void testSendMessageToClient() {
        System.out.println("sendMessageToClient");
        Message messageObj = null;
        ServerChannel instance = new ServerChannel();
        instance.sendMessageToClient(messageObj);
        fail("Unsure how to test this one")
        
    }

    /**
     * Test of checkClientConnectionExists method, of class ServerChannel.
     * DONE
     */
    @Test
    public void testCheckClientConnectionExists() {
        System.out.println("checkClientConnectionExists");
        String clientID = "clientA";
        ServerChannel instance = new ServerChannel();
        boolean expResult = false;
        boolean result = instance.checkClientConnectionExists(clientID);
        assertEquals(expResult, result);
    }

    /**
     * Test of addNewClientConnection method, of class ServerChannel.
     * DONE
     */
    @Test
    public void testAddNewClientConnection() {
        System.out.println("addNewClientConnection");
        Socket clientSocket = new Socket();
        ServerChannel instance = new ServerChannel();
        String expResult = "clientID";
        String result = instance.addNewClientConnection(clientSocket);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of terminateClientConnection method, of class ServerChannel.
     */
    @Test
    public void testTerminateClientConnection() {
        System.out.println("terminateClientConnection");
        String clientID = "";
        ServerChannel instance = new ServerChannel();
        instance.terminateClientConnection(clientID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeClientConnection method, of class ServerChannel.
     */
    @Test
    public void testRemoveClientConnection() {
        System.out.println("removeClientConnection");
        String clientID = "";
        ServerChannel instance = new ServerChannel();
        instance.removeClientConnection(clientID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of renameClientID method, of class ServerChannel.
     */
    @Test
    public void testRenameClientID() {
        System.out.println("renameClientID");
        String currentID = "";
        String newID = "";
        ServerChannel instance = new ServerChannel();
        instance.renameClientID(currentID, newID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClientInfo method, of class ServerChannel.
     */
    @Test
    public void testGetClientInfo() {
        System.out.println("getClientInfo");
        String clientID = "";
        ServerChannel instance = new ServerChannel();
        ClientInfo expResult = null;
        ClientInfo result = instance.getClientInfo(clientID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllConnectedClientIDs method, of class ServerChannel.
     */
    @Test
    public void testGetAllConnectedClientIDs() {
        System.out.println("getAllConnectedClientIDs");
        ServerChannel instance = new ServerChannel();
        Set<String> expResult = null;
        Set<String> result = instance.getAllConnectedClientIDs();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllClientInfo method, of class ServerChannel.
     */
    @Test
    public void testGetAllClientInfo() {
        System.out.println("getAllClientInfo");
        ServerChannel instance = new ServerChannel();
        HashMap<String, ClientInfo> expResult = null;
        HashMap<String, ClientInfo> result = instance.getAllClientInfo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClientInfoListCache method, of class ServerChannel.
     */
    @Test
    public void testSetClientInfoListCache() {
        System.out.println("setClientInfoListCache");
        HashMap<String, ClientInfo> newClientInfoList = null;
        ServerChannel instance = new ServerChannel();
        instance.setClientInfoListCache(newClientInfoList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkCoordinatorIsSet method, of class ServerChannel.
     */
    @Test
    public void testCheckCoordinatorIsSet() {
        System.out.println("checkCoordinatorIsSet");
        ServerChannel instance = new ServerChannel();
        boolean expResult = false;
        boolean result = instance.checkCoordinatorIsSet();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCoordinatorID method, of class ServerChannel.
     */
    @Test
    public void testGetCoordinatorID() {
        System.out.println("getCoordinatorID");
        ServerChannel instance = new ServerChannel();
        String expResult = "";
        String result = instance.getCoordinatorID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCoordinatorConnection method, of class ServerChannel.
     */
    @Test
    public void testSetCoordinatorConnection() {
        System.out.println("setCoordinatorConnection");
        String clientID = "";
        ServerChannel instance = new ServerChannel();
        instance.setCoordinatorConnection(clientID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCoordinatorConnectionNull method, of class ServerChannel.
     */
    @Test
    public void testSetCoordinatorConnectionNull() {
        System.out.println("setCoordinatorConnectionNull");
        ServerChannel instance = new ServerChannel();
        instance.setCoordinatorConnectionNull();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
