package work.universitycourse.comp1549.Modules;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import javax.swing.JTable;
import work.universitycourse.comp1549.Components.ClientInfo;
import work.universitycourse.comp1549.Components.ClientInstruction;
import work.universitycourse.comp1549.Components.Message;
import work.universitycourse.comp1549.Components.ServerChannel;

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
// #                               ServerManager                               #
// #  Controller of server initialisation, processing and client connections.  #
// #                                                                           #
// #                   1 - Constructor & Server Setup                          #
// #                   2 - UI Functions                                        #
// #                   3 - Request Handler Class                               #
// #                   3.1 - Runnable Method                                   #
// #                   3.2 - Message Processing                                #
// #                   3.3 - Instruction Processing                            #
// #                   3.4 - Specific Instruction Processes                    #
// #                   3.5 - Miscellaneous Functions                           #
// #                                                                           #
// #---------------------------------------------------------------------------#

public class ServerManager {

    private boolean serverRunning;
    private int maxClientConnections = 1024;
    private int serverPort;
    private JTable serverLogger;
    private ServerChannel serverChannel;
    private ServerSocket server = null;
    private String serverIP;

    // #-----------------------------------------------------------------------#
    // #                     1 - Constructor & Server Setup                    #
    // #-----------------------------------------------------------------------#

    public ServerManager(JTable serverLogger, String serverIP, int serverPort, int maxClientConnections) {

        this.serverIP = serverIP;
        this.serverPort = serverPort;
        this.maxClientConnections = maxClientConnections;
        this.serverLogger = serverLogger;
        this.serverChannel = new ServerChannel();

        // On object creation, initialise server.
        this.initServer();

    }

    // Set up server sockets
    private void initServer() {

        try {

            InetAddress serverAddr = InetAddress.getByName(this.serverIP);
            this.server = new ServerSocket(this.serverPort, this.maxClientConnections, serverAddr);
            this.server.setReuseAddress(true);

        } catch (IOException e) {

            InterfaceManager.executeConnectionFault();
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
            InterfaceManager.registerServerLog(this.serverLogger, "-", "-", "Connection",
                    "New Client Connected: " + clientID);

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

    // #-----------------------------------------------------------------------#
    // #                     2 - UI Functions                                  #
    // #-----------------------------------------------------------------------#


    // Used to log a message on the server
    private void displayServerLogMessage(Message messageObj) {

        String messageType = (messageObj.messageType == Message.INSTRUCTION_TYPE) ? "Instruction" : "Message";

        // If message type is an instruction, set the output to be the message type in text
        if (messageType.equals("Instruction")) {

            int instructionCode = Integer.parseInt(messageObj.message.split("<SEPERATOR>")[0]);
            messageObj.message = ClientInstruction.INSTRUCTIONS_TEXT[instructionCode];

        } else if (messageType.equals("Message") && messageObj.isServerChatMessage) {

            // Reformat output of message (needed to make the logger display a cleaner message)
            Message groupChatMessage = Message.fromString(messageObj.message);
            messageObj.message = "Group Chat Message: " + groupChatMessage.message + " FROM: "
                    + groupChatMessage.sender;

        }

        InterfaceManager.registerServerLog(ServerManager.this.serverLogger, messageObj.sender, messageObj.receiver,
                messageType, messageObj.message);

    }

    // Used when the server is logging a command from itself
    private void displayServerLogMessage(String message) {
        InterfaceManager.registerServerLog(ServerManager.this.serverLogger, "-", "-", "COMMAND", message);
    }

    // #-----------------------------------------------------------------------#
    // #                     3 - Request Handler Class                         #
    // #-----------------------------------------------------------------------#

    private class RequestHandler implements Runnable {

        // #-------------------------------------------------------------------#
        // #                       3.1 - Runnable Method                       #
        // #-------------------------------------------------------------------#

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

        // #-------------------------------------------------------------------#
        // #                       3.2 - Message Processing                    #
        // #-------------------------------------------------------------------#

        // Sends the message from the server to the client
        private void sendMessageToClient(Message messageObj) {

            // Check client is in the list
            boolean checkReceiverInChannel = ServerManager.this.serverChannel
                    .checkClientConnectionExists(messageObj.receiver);
            boolean checkSenderInChannel = ServerManager.this.serverChannel
                    .checkClientConnectionExists(messageObj.sender);

            if ((checkReceiverInChannel || messageObj.sender == "SERVER")
                    && (checkSenderInChannel || messageObj.sender == "SERVER")) {

                // Client is in list, send message
                ServerManager.this.serverChannel.sendMessageToClient(messageObj);

                // Log message on server
                ServerManager.this.displayServerLogMessage(messageObj);

            } else if (messageObj.isServerChatMessage) {

                // Tell Coordinator to output server chat message
                // Create Send Server Message Instruction
                String messageObjStr = messageObj.toString();
                String sendServerChatMessage = ClientInstruction
                        .createSendServerChatMessageInstructionString(messageObjStr);

                // Send Message to coordinator
                String coordinatorID = ServerManager.this.serverChannel.getCoordinatorID();
                Message sendInstructionMessage = new Message("SERVER", coordinatorID, sendServerChatMessage,
                        Message.INSTRUCTION_TYPE);
                ServerManager.this.serverChannel.sendMessageToClient(sendInstructionMessage);

            } else {

                // Tell sender that receiver does not exists
                System.out.println("Error try to execute message: " + messageObj.message + " from " + messageObj.sender
                        + " to " + messageObj.receiver + " of type " + messageObj.messageType);

                // Check sender is in channel otherwise message may of came from a client that has disconnected or been rejected
                if (checkSenderInChannel) {

                    Message serverMessage = new Message("SERVER", messageObj.sender,
                            "'" + messageObj.receiver + "' does not exist", Message.MESSAGE_TYPE);
                    ServerManager.this.serverChannel.sendMessageToClient(serverMessage);

                }
            }

        }

        // #-------------------------------------------------------------------#
        // #                       3.3 - Instruction Processing                #
        // #-------------------------------------------------------------------#

        private void processInstruction(Message messageObj) {

            // Get Instruction Components
            String[] instructionComponents = messageObj.message.split("<SEPERATOR>");
            int instructionType = Integer.parseInt(instructionComponents[0]);
            String[] dataComponents = instructionComponents[1].split("::");

            // Check if Coordinator is set
            if (!ServerManager.this.serverChannel.checkCoordinatorIsSet()
                    && instructionType == ClientInstruction.ESTABLISH_CONNECTION_INSTRUCTION_TYPE) {

                // Accept client as coordinator
                String clientID = dataComponents[0];
                this.executeInstructionEstablishConnection(messageObj.sender, clientID);

            } else {

                String coordinatorID = ServerManager.this.serverChannel.getCoordinatorID();

                // Process Instruction
                switch (instructionType) {

                case ClientInstruction.REJECT_JOIN_REQUEST_INSTRUCTION_TYPE:

                    // Process a reject request
                    this.executeInstructionRejectJoinRequest(messageObj);
                    break;

                case ClientInstruction.ESTABLISH_CONNECTION_INSTRUCTION_TYPE:

                    // Process connection request
                    this.executeInstructionEstablishConnection(messageObj.sender, dataComponents[0]);
                    break;

                case ClientInstruction.ACCEPT_CLIENT_CONNECTION_INSTRUCTION_TYPE:

                    // Check instruction is from coordinator
                    if (messageObj.sender.equals(coordinatorID)) {

                        // Process accept client Connection
                        this.executeInstructionAcceptClientConnection(dataComponents[0], dataComponents[1]);
                        break;

                    } else {
                        ServerManager.this
                                .displayServerLogMessage("A client with a modified request attempted connection to the server.");
                    }

                case ClientInstruction.CLIENT_DISCONNECTED_INSTRUCTION_TYPE:

                    // Process client disconnected instruction
                    this.executeInstructionClientDisconnected(dataComponents[0]);
                    break;

                case ClientInstruction.UPDATE_CLIENT_INFOS_SERVER_CACHE_INSTRUCTION_TYPE:

                    // Update the client info list cached on the server
                    this.executeInstructionUpdateClientInfoCache(dataComponents[0]);
                    break;

                default:
                    // Unknown instruction - log to console.
                    ServerManager.this.displayServerLogMessage("Server received unknown instruction - please investigate.");
                    break;

                }

            }

        }

        // #-------------------------------------------------------------------#
        // #                 3.4 - Specific Instruction Processes              #
        // #-------------------------------------------------------------------#

        // Executes the instruction used to remove a clientConnection
        private void executeInstructionRemoveConnection(String clientID) {

            // Remove client connection
            if (ServerManager.this.serverChannel.checkClientConnectionExists(clientID)) {

                // Remove Client
                ServerManager.this.serverChannel.removeClientConnection(clientID);
                ServerManager.this.displayServerLogMessage(clientID + " has left the server.");

            }

        }

        // Executes the instrution used to establish a connection from a client to the srver
        private void executeInstructionEstablishConnection(String tempID, String clientID) {

            // Check if coordinator is set
            if (ServerManager.this.serverChannel.checkCoordinatorIsSet()) {

                // Send to coordinator to determine connection

                // Define Variables
                ClientInfo clientInfo = ServerManager.this.serverChannel.getClientInfo(tempID);
                String coordinatorID = ServerManager.this.serverChannel.getCoordinatorID();
                String reviewJoinRequestString = ClientInstruction.createReviewJoinRequestInstructionString(tempID,
                        clientID, clientInfo.clientIP, clientInfo.clientPort);

                // Send to coordinator to determine connection
                Message reviewJoinRequestInstruction = new Message("SERVER", coordinatorID, reviewJoinRequestString,
                        Message.INSTRUCTION_TYPE);
                ServerManager.this.serverChannel.addMessageToChannel(reviewJoinRequestInstruction);

            } else {

                // Make client Coordinator

                // Replace Temp ID with specified ID
                ServerManager.this.serverChannel.renameClientID(tempID, clientID);

                // Tell client that they will be the coordinator
                this.makeClientCoordinator(clientID);

            }

        }

        // Executes the instruction used to accept a client connection
        private void executeInstructionAcceptClientConnection(String tempID, String clientID) {
            ServerManager.this.serverChannel.renameClientID(tempID, clientID);
        }

        // Execute the instruction used to reject a join request
        private void executeInstructionRejectJoinRequest(Message messageObj) {

            String coordinatorID = ServerManager.this.serverChannel.getCoordinatorID();
            String[] dataComponents = messageObj.message.split("<SEPERATOR>")[1].split("::");
            String clientID = dataComponents[0];

            // Check instruction is from coordinator
            if (messageObj.sender.equals(coordinatorID)) {

                ServerManager.this.serverChannel.terminateClientConnection(clientID);
                this.executeInstructionRemoveConnection(clientID);

            }

        }

        // Execute the instruction used to handle a client that has disconnected
        private void executeInstructionClientDisconnected(String clientID) {

            String coordinatorID = ServerManager.this.serverChannel.getCoordinatorID();

            // Check if coordinator disconnected
            if (clientID.equals(coordinatorID)) {

                // Set coordinator to null
                ServerManager.this.serverChannel.setCoordinatorConnectionNull();
                ServerManager.this.displayServerLogMessage("FIND NEW COORDINATOR");

                // Find new coordinator
                this.findNewCoordinator(coordinatorID);

                // Set coordinator ID to be the new coordinator
                coordinatorID = ServerManager.this.serverChannel.getCoordinatorID();

            }

            // Remove connection from client connection list cache
            this.executeInstructionRemoveConnection(clientID);

            // If coordinator present, notify them
            if (ServerManager.this.serverChannel.checkCoordinatorIsSet()) {

                // Tell coordinator about the client that has disconnected
                String notifyClientDisconnecString = ClientInstruction
                        .createNotifyClientDisconnectedInstructionString(clientID);
                Message notifyClientDisconnectInstruction = new Message("SERVER", coordinatorID,
                        notifyClientDisconnecString, Message.INSTRUCTION_TYPE);
                ServerManager.this.serverChannel.addMessageToChannel(notifyClientDisconnectInstruction);

            }

        }

        // Execute the instruction used to update the client infomation on the server cache
        private void executeInstructionUpdateClientInfoCache(String allClientInfoString) {

            HashMap<String, ClientInfo> newClientInfoList = this
                    .convertAllClientInfoStringToHashMap(allClientInfoString);
            ServerManager.this.serverChannel.setClientInfoListCache(newClientInfoList);

        }

        // #-------------------------------------------------------------------#
        // #                       3.5 - Miscellaneous Functions               #
        // #-------------------------------------------------------------------#

        // Used to make another member coordinator when a previous coordinator disconnects
        private void findNewCoordinator(String oldCoordinatorID) {

            HashMap<String, ClientInfo> clientInfoListCache = ServerManager.this.serverChannel.getAllClientInfo();

            // Check that there is another client connected
            if (clientInfoListCache.keySet().size() != 1) { // Has to be != 1 as current coordinator is still in the list           

                String newCoordinatorID = "";

                // Find new coordinator ID
                for (String currentClientID : clientInfoListCache.keySet()) {

                    if (!currentClientID.equals(oldCoordinatorID)) {
                        newCoordinatorID = currentClientID;
                        break;
                    }

                }

                this.makeClientCoordinator(newCoordinatorID);

            }

        }

        // Makes and tells the client specified that they will become the coordinator
        private void makeClientCoordinator(String clientID) {

            String becomeCoordinatorString = ClientInstruction.createBecomeCoordinatorInstructionString();
            Message setCoordinatorInstruction = new Message("SERVER", clientID, becomeCoordinatorString,
                    Message.INSTRUCTION_TYPE);
            ServerManager.this.serverChannel.addMessageToChannel(setCoordinatorInstruction);
            ServerManager.this.serverChannel.setCoordinatorConnection(clientID);

        }

        // Unpackages all client info string back to a clientInfo hashmap
        private HashMap<String, ClientInfo> convertAllClientInfoStringToHashMap(String clientInfoString) {

            HashMap<String, ClientInfo> clientInfoList = new HashMap<String, ClientInfo>();

            String[] clients = clientInfoString.split("%%");

            for (String currentClientInfo : clients) {

                String[] components = currentClientInfo.split(",");
                String clientID = components[0];
                String clientIP = components[1];
                int clientPort = Integer.parseInt(components[2]);

                ClientInfo clientInfoObj = new ClientInfo(clientID, clientIP, clientPort);
                clientInfoList.put(clientID, clientInfoObj);

            }

            return clientInfoList;

        }

    }

}

// Make sure clients dont attempt to connect with same port             [CHECK]