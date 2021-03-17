
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
 * @author Gabriel
 * @author Gabriel
 * @author Gabriel
 */
public class ClientInfoTest {
    
    public ClientInfoTest() {
    }

    /**
     * Test of toString method, of class ClientInfo.
     */
    
    @Test
    public void testToString() {
        String clientID = "TestID";
        String clientIP = "192.168.0.1";
        int clientPort = 11;
        ClientInfo instance = new ClientInfo(clientID,clientIP,clientPort);
        System.out.println("toString");
        String expResult = "TestID,192.168.0.1,11";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
    @Test
    public void testToStringError() {
        String clientID = "TestID";
        String clientIP = "";
        int clientPort = 11;
        ClientInfo instance = new ClientInfo(clientID,clientIP,clientPort);
        System.out.println(instance);
        System.out.println("toString");
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
    }
}
