/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package work.universitycourse.comp1549.Modules;

import java.util.HashMap;
import java.util.Set;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import work.universitycourse.comp1549.Components.ClientInfo;

/**
 *
 * @author Gabriel
 */
public class ClientManagerTest {
    
    public ClientManagerTest() {
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
     * Test of sendMessage method, of class ClientManager.
     */
    @Test
    public void testSendMessage() {
        System.out.println("sendMessage");
        String receiver = "";
        String message = "";
        ClientManager instance = null;
        instance.sendMessage(receiver, message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addClientInfoToLocalList method, of class ClientManager.
     */
    @Test
    public void testAddClientInfoToLocalList() {
        System.out.println("addClientInfoToLocalList");
        String clientID = "";
        ClientInfo clientInfoObj = null;
        ClientManager instance = null;
        instance.addClientInfoToLocalList(clientID, clientInfoObj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClientInfoFromLocalList method, of class ClientManager.
     */
    @Test
    public void testGetClientInfoFromLocalList() {
        System.out.println("getClientInfoFromLocalList");
        String clientID = "";
        ClientManager instance = null;
        ClientInfo expResult = null;
        ClientInfo result = instance.getClientInfoFromLocalList(clientID);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllClientIDsFromLocalList method, of class ClientManager.
     */
    @Test
    public void testGetAllClientIDsFromLocalList() {
        System.out.println("getAllClientIDsFromLocalList");
        ClientManager instance = null;
        Set<String> expResult = null;
        Set<String> result = instance.getAllClientIDsFromLocalList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllClientsInfoFromLocalList method, of class ClientManager.
     */
    @Test
    public void testGetAllClientsInfoFromLocalList() {
        System.out.println("getAllClientsInfoFromLocalList");
        ClientManager instance = null;
        HashMap<String, ClientInfo> expResult = null;
        HashMap<String, ClientInfo> result = instance.getAllClientsInfoFromLocalList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
