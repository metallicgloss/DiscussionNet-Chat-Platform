package work.universitycourse.comp1549.Modules;

import java.net.Socket;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket; // USED
import work.universitycourse.comp1549.Components.ServerChannel; // USED
import work.universitycourse.comp1549.Components.ClientInstruction;
import work.universitycourse.comp1549.Components.Message;
import work.universitycourse.comp1549.Modules.InterfaceManager;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JFrame;
import javax.swing.JTable; // USED

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
 *              Constructor & Server Setup
 *              
 *                  Request Handler Class
 *                  
 *                          > Runnable Method
 *                          
 *                          > Message Processing
 *                          
 *                          > Instruction Processing
 *                          
 *                          > Specific Instruction Processes
 * 
 * 
 */


public class ServerManager {
    private ServerSocket server;
    private ServerChannel serverChannel;
    private int serverPort;
    private String serverIP;
    private int maxClientConnections = 1024;
    private boolean serverRunning;
    private JTable serverLogger;

    // ===================================================
    // -           Constructor & Server Setup            -
    // ===================================================

        public ServerManager(JTable serverLogger, String serverIP, int serverPort, int maxClientConnections) {

            this.serverIP = serverIP;
            this.serverPort = serverPort;
            this.maxClientConnections = maxClientConnections;
            this.serverLogger = serverLogger;
            this.serverChannel = new ServerChannel();

            this.initServer();

        }

        // Set up server sockets
        private void initServer() {
            
            try {

            InetAddress serverAddr = InetAddress.getByName(this.serverIP);
            this.server = new ServerSocket(this.serverPort, this.maxClientConnections, serverAddr);
            this.server.setReuseAddress(true);

            } catch (IOException e) {

                InterfaceManager.displayError(e, "Failed to start server on port!");
                this.serverRunning = false;
    
            }

        }

        // Starts the server
        public void startServer() {

            // Start Request handler thread
            RequestHandler requestHandler = new RequestHandler();
            Thread requestHandlerThread = new Thread(requestHandler);
            requestHandlerThread.start();
            InterfaceManager.registerServerLog(this.serverLogger, "-", "-", "Startup", "Server Started.");
            
            // Accept new clients while the thread is running
            this.serverRunning = true;
            while (this.serverRunning) {
                this.acceptNewClient();
            }
            this.closeServer();

        }

        // Accepts a new client connection
        private void acceptNewClient() {

            try {
                String clientID;

                // Accept new client and add them to the channel
                Socket clientSocket = this.server.accept();
                clientID = this.serverChannel.addNewClientConnection(clientSocket);
                InterfaceManager.registerServerLog(this.serverLogger, "-", "-", "Connection", "New Client Connected.");

                // Set coordinator if not already set
                if (! this.serverChannel.checkCoordinatorIsSet()) {
                    // Tell client that it will be the coordinator
                    String becomeCoordinatorString = ClientInstruction.createBecomeCoordinatorInstructionString();
                    Message setCoordinatorInstruction = new Message("SERVER", clientID, becomeCoordinatorString, Message.INSTRUCTION_TYPE);
                    this.serverChannel.addMessageToChannel(setCoordinatorInstruction);
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

    // ===================================================
    // -               Request Handler Class            -
    // ===================================================

        private class RequestHandler implements Runnable {

            public RequestHandler() {}

            // ===================================================
            // -                 Runnable Method                 -
            // ===================================================

                @Override
                public void run() {

                    while (true) {

                        // Get next message in channel
                        Message messageObj = ServerManager.this.serverChannel.getNextMessageFromChannel();

                        // Process next message if one is present
                        if (messageObj != null) {

                            // Check if its a instruction for server or someone else
                            if (messageObj.receiver.equals("SERVER") && messageObj.messageType == Message.INSTRUCTION_TYPE) {
                                
                                // Process instruction to server
                                this.processInstruction(messageObj);

                            } else {
                                this.sendMessageToClient(messageObj);   
                            }

                        }

                    }

                }
            
            // ===================================================
            // -               Message Processing                -
            // ===================================================

                // Sends the message from the server to the client
                private void sendMessageToClient(Message messageObj) {

                    // Check client is in the list
                    if (ServerManager.this.serverChannel.checkClientConnectionExists(messageObj.receiver)) {

                        // Client is in list, send message
                        ServerManager.this.serverChannel.sendMessageToClient(messageObj.receiver, messageObj);
                        InterfaceManager.registerServerLog(ServerManager.this.serverLogger, messageObj.sender, messageObj.receiver, "Message", messageObj.message);

                    } else {

                        // Tell sender that receiver does not exists
                        Message serverMessage = new Message("SERVER", messageObj.sender, "'" + messageObj.receiver + "' does not exist", Message.MESSAGE_TYPE);
                        ServerManager.this.serverChannel.sendMessageToClient(messageObj.sender, serverMessage);

                    }

                }
            
            // ===================================================
            // -             Instruction Processing              -
            // ===================================================

                private void processInstruction(Message messageObj) {

                    // Get Instruction Components
                    String[] instructionComponents = messageObj.message.split("<SEPERATOR>");

                    // Process Instruction
                    switch (instructionComponents[0]) {

                        case "REMOVE CONNECTION":
                            
                            // CHECK INSTRUCTION IS FROM SERVER OR COORDINATOR
                            String coordinatorID = ServerManager.this.serverChannel.getCoordinatorID();
                            boolean isInstructionFromServer = messageObj.receiver.equals(messageObj.sender);
                            boolean isInstructionFromCoordinator = messageObj.sender.equals(coordinatorID);
                            if (isInstructionFromServer || isInstructionFromCoordinator) {

                                String clientID = instructionComponents[1];
                                this.executeRemoveConnectionInstruction(clientID);

                                // Get new coordinator if this client was the coordinator
                                if (clientID.equals(coordinatorID)) {

                                    ServerManager.this.serverChannel.setCoordinatorConnectionNull();
                                    InterfaceManager.registerServerLog(ServerManager.this.serverLogger, "-", "-", "COMMAND", "FIND NEW COORDINATOR");
                                    // TODO execute instruction getNewCoordinator

                                }

                            } else {
                                // Create custome error message to handle unauthorised instruction
                            }

                            break;
                        
                        case "ADD CLIENT TO LIST":
                            // CHECK Sender is coordinator
                            // DATA FORMAT <clientID><SEPERATOR><clientPort><SEPERATOR><clientIP>
                        
                        default:
                            // TODO How to handle a unknown instruction
                            break;
                            

                    }

                }
            
            // ===================================================
            // -          Specific Instruction Processes         -
            // ===================================================

            // Executes the instruction used to remove a clientConnection
            private void executeRemoveConnectionInstruction(String clientID) {

                // Remove client connection
                if (ServerManager.this.serverChannel.checkClientConnectionExists(clientID)) {

                    // Remove Client
                    ServerManager.this.serverChannel.removeClientConnection(clientID);
                    InterfaceManager.registerServerLog(ServerManager.this.serverLogger, "-", "-", "COMMAND", clientID + " has left the server.");

                }

            }



        }

}
