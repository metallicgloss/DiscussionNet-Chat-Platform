package work.universitycourse.comp1549.Modules;

import java.net.Socket;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import work.universitycourse.comp1549.Components.Channel;
import work.universitycourse.comp1549.Components.ClientInstruction;
import work.universitycourse.comp1549.Components.Message;
import work.universitycourse.comp1549.Modules.InterfaceManager;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
        
/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 */
public class ServerManager {
    private ServerSocket server;
    private Channel serverChannel;
    private int port;
    private String serverIP;
    private int maxConnections = 1024;
    private boolean serverRunning;
    private JTable serverLog;

    public ServerManager() {
        this.initServer();
    }

    public ServerManager(JTable serverLog, String ip, int port, int maxConnections) {
        this.serverIP = ip;
        this.port = port;
        this.maxConnections = maxConnections;
        this.serverLog = serverLog;
        
        this.initServer();
    }

    // Starts the server
    public void startServer() {
        // Start Request handler thread
        RequestHandler requestHandler = new RequestHandler(this.serverLog);
        Thread requestHandlerThread = new Thread(requestHandler);
        requestHandlerThread.start();
        InterfaceManager.registerServerLog(this.serverLog, "-", "-", "Startup", "Server Started.");
        
        // Accept new clients while the thread is running
        this.serverRunning = true;
        while (this.serverRunning) {
            this.acceptNewClient();
        }
        this.closeServer();
    }

    // Initialises properites needs for the server to run
    private void initServer() {
        // Get server channel
        this.serverChannel = Channel.getChannel();

        // Initialise server socket
        // TODO replace with check port available
        try {
            InetAddress serverAddr = InetAddress.getByName(this.serverIP);
            this.server = new ServerSocket(this.port, this.maxConnections, serverAddr);
            this.server.setReuseAddress(true);
        } catch (IOException e) {
            InterfaceManager.displayError(e, "Failed to start server on port!");
            this.serverRunning = false;
        }
    }

    // Tells the server to accept new client connection requests
    private void acceptNewClient() {
        try {
            String clientID;

            // Accept new client and add them to the channel
            Socket clientSocket = this.server.accept();
            clientID = this.serverChannel.addClientConnection(clientSocket);
            InterfaceManager.registerServerLog(this.serverLog, "-", "-", "Connection", "New Client Connected.");

            // Set coordinator if not already set
            if (this.serverChannel.getCoordinatorConnection() == null) {
                // Tell client that it will be the coordinator
                String becomeCoordinatorString = ClientInstruction.createBecomeCoordinatorInstructionString();
                Message setCoordinatorInstruction = new Message("SERVER", clientID, becomeCoordinatorString, Message.INSTRUCTION_TYPE);
                this.serverChannel.addMessage(setCoordinatorInstruction);
                this.serverChannel.setCoordinatorConnection(clientID);
            }

        } catch (IOException e) {
            InterfaceManager.displayError(e, "Failed to add new client!");
        }
    }

    // Closes the server
    private void closeServer() {
        try {
            if (this.server != null) {
                this.server.close();
            }
        } catch (IOException e) {
            InterfaceManager.displayError(e, "Failed to stop server!");
        } finally {
            this.serverRunning = false;
        }
    }

    // Used to handle messages received from clients
    private static class RequestHandler implements Runnable {

        private Channel serverChannel;
        private JTable serverLog;

        public RequestHandler(JTable serverLog) {
            this.serverChannel = Channel.getChannel();
            this.serverLog = serverLog;
        }

        @Override
        public void run() {
            while (true) {
                // Get next message in channel
                Message messageObj = this.serverChannel.getNextMessage();
                
                // Process next message if one is present
                if (messageObj != null) {
                    if (this.serverChannel.checkClientConnectionExists(messageObj.receiver)) {
                        this.serverChannel.getClientConnection(messageObj.receiver).sendMessage(messageObj);
                        InterfaceManager.registerServerLog(this.serverLog, messageObj.sender, messageObj.receiver, "Message", messageObj.message);
                    } else {
                        // TODO How to handle a receiver that is not in the clientconnections hash map
                    }
                }

                // Put the thread to sleep for a bit before checking again
                this.wait(100);
            }
        }

        private void wait(int ms) {
            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                InterfaceManager.displayError(e, "Thread sleep error occurred");
            }
        }

    }
}

/**
 * TODO
 * Add coordinator position [CHECK]
 * Check coordinator is present else remove them and assign new coordinator
 */