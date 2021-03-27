package work.universitycourse.comp1549.Modules;



import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import work.universitycourse.comp1549.Components.ClientInfo;

/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 *
 */

// #---------------------------------------------------------------------------#
// #                                 Contents                                  #
// #---------------------------------------------------------------------------#
// #                                                                           #
// #                           ClientManager Testing                           #
// #        Unit and Integration tests of Client & Server functionality.       #
// #                                                                           #
// #               1 - Initialise Server and Clients                           #
// #               2 - Add Client to Local List                                #
// #               3 - Get All Client IDs from List                            #
// #               4 - Get All Client Info from List                           #
// #               5 - Verify Client Status                                    #
// #               6 - Verify Client List                                      #
// #                                                                           #
// #---------------------------------------------------------------------------#

public class ClientManagerTest {

    private ServerManager server;
    private ClientManager clientA;
    private ClientManager clientB;

    public ClientManagerTest() {
    }

    // #-----------------------------------------------------------------------#
    // #                    1. Initalise Server and Clients                    #
    // #-----------------------------------------------------------------------#

    @BeforeEach
    public void setUp() {
        // Execute server as thread.
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

        // Configure client 1
        this.clientA = new ClientManager(new JTabbedPane(), new JFrame(), new JLabel(), "127.0.0.1", 9090, "ClientA",
                "127.0.0.1", 9091);

        // Configure client 2
        this.clientB = new ClientManager(new JTabbedPane(), new JFrame(), new JLabel(), "127.0.0.1", 9090, "ClientB",
                "127.0.0.1", 9092);
    }

    // #-----------------------------------------------------------------------#
    // #                      2. Add Client to Local List                      #
    // #-----------------------------------------------------------------------#

    @Test
    public void testAddClientInfoToLocalList() throws IOException {
        // Initialise new client, add to local list of Client A.
        this.clientA.addClientInfoToLocalList("ClientC", new ClientInfo("ClientC", new Socket(InetAddress.getByName("127.0.0.1"), 9090)));

        // Check if ClientC has been added correctly, if get function returns correctly.
        assertEquals("ClientC", this.clientA.getClientInfoFromLocalList("ClientC").clientID);
    }

    // #-----------------------------------------------------------------------#
    // #                    3. Get All Client IDs from List                    #
    // #-----------------------------------------------------------------------#

    @Test
    public void testGetAllClientIDsFromLocalList() throws IOException {
        // Create new client and add to local list.
        this.clientA.addClientInfoToLocalList("ClientC", new ClientInfo("ClientC", new Socket(InetAddress.getByName("127.0.0.1"), 9090)));
        Set<String> expResult = new HashSet<>();
        expResult.add("ClientC");

        // Verify result matches expected.
        assertEquals(expResult, clientA.getAllClientIDsFromLocalList());

    }

    // #-----------------------------------------------------------------------#
    // #                   4. Get All Client Info from List                    #
    // #-----------------------------------------------------------------------#

    @Test
    public void testGetAllClientsInfoFromLocalList() throws IOException {
        // Define IP object.
        InetAddress localIP = InetAddress.getByName("127.0.0.1");

        // Create new client and add to local list.
        this.clientA.addClientInfoToLocalList("ClientC", new ClientInfo("ClientC", new Socket(localIP, 9090)));

        // Define info list.
        HashMap<String, ClientInfo> expResult = new HashMap<>();
        expResult.put("ClientC", new ClientInfo("ClientC", new Socket(localIP, 9090)));
        HashMap<String, ClientInfo> result = this.clientA.getAllClientsInfoFromLocalList();
        
        // Check formatting of generated and original matches.
        assertEquals(expResult.get("ClientC").clientID, result.get("ClientC").clientID);

    }

    // #-----------------------------------------------------------------------#
    // #                        5. Verify Client Status                        #
    // #-----------------------------------------------------------------------#

    @Test
    public void testGetClientStatus() {
        // Check client status (disconnected)
        assertEquals(false, this.clientA.getClientStatus());
    }

    // #-----------------------------------------------------------------------#
    // #                         6. Verify Client List                         #
    // #-----------------------------------------------------------------------#

    @Test
    public void testGetAllClientsInfoFromLocalListAsFormattedString() {
        // Check Length matches as formatted.
        assertEquals(" Client ID 	 Client IP 	 Client Port ".length(), this.clientA.getAllClientsInfoFromLocalListAsFormattedString().length());
    }

}
