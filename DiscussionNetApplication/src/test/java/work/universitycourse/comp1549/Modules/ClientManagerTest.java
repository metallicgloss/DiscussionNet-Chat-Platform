package work.universitycourse.comp1549.Modules;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import work.universitycourse.comp1549.Components.ClientInfo;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 *
 * @author Gabriel
 */
public class ClientManagerTest {

    private ServerManager server;
    private ClientManager clientA;
    private ClientManager clientB;

    public ClientManagerTest() {
    }

    public void delay(int time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    public void setUp() {
        new Thread(new Runnable() {
            private JTable serverLog;
            private String serverIPAddress;
            private int serverPort;

            public Runnable init(JTable serverLog, String serverIPAddress, int serverPort) {
                this.serverLog = serverLog;
                this.serverIPAddress = serverIPAddress;
                this.serverPort = serverPort;
                return this;
            }

            @Override
            public void run() {
                ServerManager serverInstance = new ServerManager(this.serverLog, this.serverIPAddress, this.serverPort);
                serverInstance.startServer();
            }
        }.init(new JTable(), "127.0.0.1", 9090)).start();

        this.clientA = new ClientManager(new JTabbedPane(), new JFrame(), new JLabel(), "127.0.0.1", 9090, "ClientA",
                "127.0.0.1", 9091);
        this.clientB = new ClientManager(new JTabbedPane(), new JFrame(), new JLabel(), "127.0.0.1", 9090, "ClientB",
                "127.0.0.1", 9092);
    }

    /**
     * Test of addClientInfoToLocalList method, of class ClientManager.
     * @throws IOException
     */
    @Test
    public void testAddClientInfoToLocalList() throws IOException {
        this.clientA.addClientInfoToLocalList("ClientC", new ClientInfo("ClientC", new Socket(InetAddress.getByName("127.0.0.1"), 9090)));

        assertEquals("ClientC", this.clientA.getClientInfoFromLocalList("ClientC").clientID);
    }

    /**
     * Test of getClientInfoFromLocalList method, of class ClientManager.
     * @throws IOException
     */
    @Test
    public void testGetClientInfoFromLocalList() throws IOException {
        this.clientA.addClientInfoToLocalList("ClientC", new ClientInfo("ClientC", new Socket(InetAddress.getByName("127.0.0.1"), 9090)));
        assertEquals( "ClientC", this.clientA.getClientInfoFromLocalList("ClientC").clientID);
    }

    /**
     * Test of getAllClientIDsFromLocalList method, of class ClientManager.
     * @throws IOException
     */
    @Test
    public void testGetAllClientIDsFromLocalList() throws IOException {
        this.clientA.addClientInfoToLocalList("ClientC", new ClientInfo("ClientC", new Socket(InetAddress.getByName("127.0.0.1"), 9090)));
        Set<String> expResult = new HashSet<>();
        expResult.add("ClientC");
        assertEquals(expResult, clientA.getAllClientIDsFromLocalList());

    }

    /**
     * Test of getAllClientsInfoFromLocalList method, of class ClientManager.
     * @throws IOException
     */
    @Test
    public void testGetAllClientsInfoFromLocalList() throws IOException {
        InetAddress localIP = InetAddress.getByName("127.0.0.1");

        this.clientA.addClientInfoToLocalList("ClientC", new ClientInfo("ClientC", new Socket(localIP, 9090)));
        HashMap<String, ClientInfo> expResult = new HashMap<>();

        expResult.put("ClientC", new ClientInfo("ClientC", new Socket(localIP, 9090)));
        HashMap<String, ClientInfo> result = this.clientA.getAllClientsInfoFromLocalList();
        
        assertEquals(expResult.get("ClientC").clientID, result.get("ClientC").clientID);

    }

    /**
     * Test of getClientStatus method, of class ClientManager.
     */
    @Test
    public void testGetClientStatus() {
        assertEquals(false, this.clientA.getClientStatus());
    }

    /**
     * Test of getAllClientsInfoFromLocalListAsFormattedString method, of class ClientManager.
     */
    @Test
    public void testGetAllClientsInfoFromLocalListAsFormattedString() {
        assertEquals(" Client ID 	 Client IP 	 Client Port ".length(), this.clientA.getAllClientsInfoFromLocalListAsFormattedString().length());
    }

}
