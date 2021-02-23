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
                    if (messageObj.messageType == Message.MESSAGE_TYPE) {

                        // Process Message Object of type message
                        this.processMessage(messageObj);
                        
                    } else if (messageObj.messageType == Message.INSTRUCTION_TYPE) {

                        // Process Instruction Object of type instruction
                        this.processInstruction(messageObj);
                    }
                }

                // Put the thread to sleep for a bit before checking again
                this.wait(100);
            }

        }

        // Tells the thread to sleep a certain amount of time
        private void wait(int ms) {

            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                InterfaceManager.displayError(e, "Thread sleep error occurred");
            }

        }

        // ===================================================
        // -   Process Messages / Instructions in Channel    -
        // ===================================================

        // Processes a Message object of type message
        private void processMessage(Message messageObj) {

            // Check client is in the list
            if (this.serverChannel.checkClientConnectionExists(messageObj.receiver)) {

                // Receiver is in list, therefore send the message to them
                this.serverChannel.getClientConnection(messageObj.receiver).sendMessage(messageObj);
                InterfaceManager.registerServerLog(this.serverLog, messageObj.sender, messageObj.receiver, "Message", messageObj.message);

            } else {

                // Tell sender that receiver does not exists
                Message serverMessage = new Message("SERVER", messageObj.sender, "'" + messageObj.receiver + "' does not exist", Message.MESSAGE_TYPE);
                this.serverChannel.getClientConnection(messageObj.sender).sendMessage(serverMessage);

            }

        }

        private void processInstruction(Message messageObj) {

            // Handle Instruction
            if (messageObj.sender.equals(messageObj.receiver) && messageObj.receiver.equals("SERVER")) {

                // Message is from the server itself
                this.processInstructionsFromServer(messageObj);
                
            } else {
                
                // Message is from a client
                this.processInstructionFromClient(messageObj);
            }   

        }

        // Processes instrutions create by the sever iteself (usually created by one of the nested class to communicate to another nested class)
        private void processInstructionsFromServer(Message messageObj) {
            // Seperate instruction components
            String[] instructionComponents = messageObj.message.split("<SEPERATOR>");

            // Process Instruction
            switch(instructionComponents[0]) {
                case "REMOVE CONNECTION":

                    String clientID = instructionComponents[1];

                    // Check connection is in client hash map
                    if (this.serverChannel.checkClientConnectionExists(clientID)) {
                        // Check client is coordinater
                        boolean isClientCoordinator = clientID.equals(this.serverChannel.getCoordinatorID());
                        
                        // Remove client
                        this.serverChannel.removeClientConnection(clientID);
                        InterfaceManager.registerServerLog(this.serverLog, "-", "-", "COMMAND", clientID + " has left the server.");
                        
                        if (isClientCoordinator) {
                            // Get new coordinator as current has been terminated
                            this.serverChannel.setCoordinatorConnectionNull();
                            InterfaceManager.registerServerLog(this.serverLog, "-", "-", "COMMAND", "FIND NEW COORDINATOR");
                        }
                    }
                    break;

                default:
                    // TODO How to handle a unknown instruction
                    break;
            }

        }

        private void processInstructionFromClient(Message messageObj) {
            // Seperate instruction components
            String[] instructionComponents = messageObj.message.split("<SEPERATOR>");

            switch (instructionComponents[0]) {
                default:
                    // TODO How to handle unknown instruction
                    break;
            }

        }

    }
}

/**
 * TODO
 * Add coordinator position [CHECK]
 * Check coordinator is present else remove them [CHECK]
 * Refactor code
 * Assign new coordinator if old has been removed
 */