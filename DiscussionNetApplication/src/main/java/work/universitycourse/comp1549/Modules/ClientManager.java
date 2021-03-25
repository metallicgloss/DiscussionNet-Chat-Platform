package work.universitycourse.comp1549.Modules;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.BindException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import work.universitycourse.comp1549.Components.ClientInfo;
import work.universitycourse.comp1549.Components.ClientInstruction;
import work.universitycourse.comp1549.Components.Message;
import work.universitycourse.comp1549.Interfaces.Client.ClientServerConnection;

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
// #                               ClientManager                               #
// #  Controller of client initialisation, processing and client connections.  #
// #                                                                           #
// #                   1 - Constructor                                         #
// #                   2 - UI Functions                                        #
// #                   3 - Local Client Info List Handler                      #
// #                   4 - Connection Handling                                 #
// #                   5 - Instructions Queue Class                            #
// #                   5.1 - Queue Processing                                  #
// #                   6 - Server Listener Class                               #
// #                   6.1 - Runnable Method                                   #
// #                   6.2 - Messaging and Thread Functions                    #
// #                   7 - Instructions Queue Class                            #
// #                   7.1 - Runnable Method                                   #
// #                   7.2 - Specific Instruction Processing                   #
// #                   7.3 - Messaging and Thread Functions                    #
// #                                                                           #
// #---------------------------------------------------------------------------#

@SuppressWarnings("rawtypes")
public class ClientManager {

    private boolean isClientRunning = true;
    private boolean isCoordinator = false;
    private ClientInfo clientInfo;
    private InstructionsQueue instructionsQueue = new InstructionsQueue();
    private JFrame messagePanel;
    private JLabel coordinatorIDLabel;
    private JTabbedPane messagePane;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Socket clientSocket;
    private String clientID;
    private String coordinatorID = "";

    private HashMap<String, ClientInfo> clientListLocal = new HashMap<String, ClientInfo>();

    // #-----------------------------------------------------------------------#
    // #                            1 - Constructor                            #
    // #-----------------------------------------------------------------------#

    public ClientManager(JTabbedPane messagePane, JFrame messagePanel, JLabel coordinatorIDLabel, String serverIP,
            int serverPort, String clientID, String clientIP, int clientPort) {

        try {

            // Create socket and check that port is free
            InetAddress inetAddress = InetAddress.getByName(clientIP);
            try {

                this.clientSocket = new Socket(serverIP, serverPort, inetAddress, clientPort);

                // Define other variables
                this.messagePane = messagePane;
                this.messagePanel = messagePanel;
                this.coordinatorIDLabel = coordinatorIDLabel;
                this.clientID = clientID;
                this.clientInfo = new ClientInfo(clientID, clientIP, clientPort);
                this.outputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
                this.inputStream = new ObjectInputStream(this.clientSocket.getInputStream());

                // Start Threads
                new Thread(new ServerListener()).start();
                new Thread(new InstructionHandler()).start();

                // Establish contact with server

                // Create 'Establish Connection' Instruction
                String establishConnectionInstructionString = ClientInstruction
                        .createEstablishConnectionInstructionString(this.clientID);
                ClientInstruction establishConnectionInstruction = new ClientInstruction(
                        establishConnectionInstructionString);

                // Add Instruction to queue
                this.instructionsQueue.addInstructionToQueue(establishConnectionInstruction);

            } catch (BindException e) {
                this.isClientRunning = false;
                InterfaceManager.executeConnectionFault();
            }

        } catch (IOException e) {
            this.isClientRunning = false;
            InterfaceManager.executeConnectionFault();
        } catch (ClientInstruction.InstructionNotExistException | ClientInstruction.InstructionFormatException
                | ClientInstruction.DataFormatException e) {
        }

    }

    // #-----------------------------------------------------------------------#
    // #                            2 - UI Functions                           #
    // #-----------------------------------------------------------------------#

    /**
     * IMPORTANT NOTE
     * 
     * Not all UI functions are found under this section. Some can be found in other sections such as 'Local Client Info List Handler'
    */

    // Allows the UI to send a message
    public void sendMessage(String receiver, String message, boolean isServerChatMessage) {

        try {

            String sendMessageInstruction = ClientInstruction.createSendMessageInstructionString(receiver, message,
                    isServerChatMessage);
            ClientInstruction instructionObj = new ClientInstruction(sendMessageInstruction);
            this.instructionsQueue.addInstructionToQueue(instructionObj);

        } catch (ClientInstruction.InstructionNotExistException | ClientInstruction.InstructionFormatException
                | ClientInstruction.DataFormatException e) {
        }

    }

    // Returns the coordinator ID
    public String getCoordinatorID() {
        return this.coordinatorID;
    }

    // Returns the client Status
    public Boolean getClientStatus() {
        return this.isClientRunning;
    }

    // #-----------------------------------------------------------------------#
    // #                  3 - Local Client Info List Handler                   #
    // #-----------------------------------------------------------------------#

    // Adds an enrty to the local client list
    public void addClientInfoToLocalList(String clientID, ClientInfo clientInfoObj) {

        if (!this.clientListLocal.containsKey(clientID)) {
            this.clientListLocal.put(clientID, clientInfoObj);
        }

    }

    // Returns the client info object assigned with the specific clientID in the local client list. Will Return null if clientID is not in the list
    public ClientInfo getClientInfoFromLocalList(String clientID) {

        ClientInfo clientInfo = null;

        if (this.clientListLocal.containsKey(clientID)) {
            clientInfo = this.clientListLocal.get(clientID);
        }

        return clientInfo;

    }

    // Returns all the client IDs int he local client list
    public Set<String> getAllClientIDsFromLocalList() {
        return this.clientListLocal.keySet();
    }

    // Returns all the client Info objects in the local client list
    public HashMap<String, ClientInfo> getAllClientsInfoFromLocalList() {
        return this.clientListLocal;
    }

    // Return formatted list of client as string.
    public String getAllClientsInfoFromLocalListAsFormattedString() {

        String allClientsInfoString = String.format("%s \t %s \t %s \n", "Client ID", "Client IP", "Client Port");

        for (String clientID : this.clientListLocal.keySet()) {

            try {

                ClientInfo clientInfo = this.clientListLocal.get(clientID);
                allClientsInfoString += String.format("%s \t %s \t %s \n", clientInfo.clientID, clientInfo.clientIP,
                        String.valueOf(clientInfo.clientPort));

            } catch (NumberFormatException e) {
            }

        }

        return allClientsInfoString;

    }

    // Removes a client info entry from the local client list
    private void removeClientInfoFromLocalList(String clientID) {

        if (this.clientListLocal.containsKey(clientID)) {
            this.clientListLocal.remove(clientID);
        }

    }

    // Packages all client info entries into a single string
    private String getAllClientsInfoFromLocalListAsString() {

        String allClientsInfoString = "";

        for (String clientID : this.clientListLocal.keySet()) {

            ClientInfo clientInfo = this.clientListLocal.get(clientID);
            allClientsInfoString += clientInfo.toString() + "%%";

        }

        // Remove last 2 %% at the end
        if (allClientsInfoString.length() > 2) {
            allClientsInfoString = allClientsInfoString.substring(0, allClientsInfoString.length() - 2);
        } else {
            allClientsInfoString = "";
        }

        return allClientsInfoString;

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

    // Sets the clientInfoListLocal to a predefined hashmap
    private void setLocalClientInfoList(HashMap<String, ClientInfo> clientInfoList) {
        this.clientListLocal = clientInfoList;
    }

    // #-----------------------------------------------------------------------#
    // #                        4 - Connection Handling                        #
    // #-----------------------------------------------------------------------#

    // Server Connection Terminated
    private void endClientConnection() {

        this.isClientRunning = false;
        this.closeSocket();
        while (messagePane.getTabCount() > 0) {
            messagePane.remove(0);
        }
        InterfaceManager.changeWindow(messagePanel, new ClientServerConnection());

    }

    // Closes the socket connection used by the client
    private void closeSocket() {

        try {
            this.clientSocket.close();
        } catch (IOException e) {
        }

    }

    // #-----------------------------------------------------------------------#
    // #                     5 - Instructions Queue Class                      #
    // #-----------------------------------------------------------------------#

    /**
    * IMPORTANT NOTE
    * 
    * The instruction manager is used to allow the other nested classes to communicate with each other. The instruction manager implements a queue style
    * array to ensure that instructions are executed using the FIFO principle. One class will add instructions to the queue, the other will process them.
    * 
    */
    private class InstructionsQueue {

        private Deque<ClientInstruction> instructionsQueue = new ArrayDeque<ClientInstruction>();

        public InstructionsQueue() {
        }

        // #-------------------------------------------------------------------#
        // #                       5.1 - Queue Processing                      #
        // #-------------------------------------------------------------------#

        // Inserts an instruction to the list of instructions (Inserted using FIFO)
        public void addInstructionToQueue(ClientInstruction instructionObj) {
            this.instructionsQueue.addLast(instructionObj);
        }

        // Returns the next instruction in the list (Fetched using FIFO)
        public ClientInstruction getNextInstruction() {
            return this.instructionsQueue.poll();
        }

    }

    // #-----------------------------------------------------------------------#
    // #                       6 - Server Listener Class                       #
    // #-----------------------------------------------------------------------#
    /**
     * IMPORTANT NOTE
     * 
     * A threadable that periodically will check for new messages from the server and will create instructions based on the server's messages for the client to process.
     */

    private class ServerListener implements Runnable {

        public ServerListener() {
        }

        // #-------------------------------------------------------------------#
        // #                       6.1 - Runnable Method                       #
        // #-------------------------------------------------------------------#

        @Override
        public void run() {

            while (ClientManager.this.isClientRunning) {

                Message serverResponse = this.getMessage();

                if (serverResponse != null) {

                    // Handle Requests
                    switch (serverResponse.messageType) {

                    case Message.INSTRUCTION_TYPE:

                        try {

                            // Convert server response string to an instruction
                            ClientInstruction instructionFromServer = new ClientInstruction(serverResponse.message);

                            // Add instruction to instruction queue
                            ClientManager.this.instructionsQueue.addInstructionToQueue(instructionFromServer);

                        } catch (ClientInstruction.InstructionNotExistException
                                | ClientInstruction.InstructionFormatException
                                | ClientInstruction.DataFormatException e) {
                            InterfaceManager.displayError(e, "Mangled instruction - unhandled / unexpected error.");
                        }

                        break;

                    case Message.MESSAGE_TYPE:

                        if (serverResponse.isServerChatMessage) {

                            // If message has been received from the group chat channel.

                            // Convert child message string into actual message object
                            Message serverChatMessage = Message.fromString(serverResponse.message);
                            InterfaceManager.displayMessage(messagePane, serverChatMessage.timestamp, "Received",
                                    serverChatMessage.sender, serverChatMessage.message, true);

                        } else {
                            InterfaceManager.displayMessage(messagePane, serverResponse.timestamp, "Received",
                                    serverResponse.sender, serverResponse.message, false);
                        }
                        break;

                    default:

                        InterfaceManager.displayWarning(
                                "Unknown Message Type Sent: " + Integer.toString(serverResponse.messageType));
                        break;

                    }

                }

                this.wait(100);

            }

        }

        // #-------------------------------------------------------------------#
        // #                6.2 - Messaging and Thread Functions               #
        // #-------------------------------------------------------------------#

        // Returns the next message sent by the server
        public Message getMessage() {

            Message messageObj = null;
            try {
                messageObj = (Message) ClientManager.this.inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {

                // Display disconnection message if occurred unexpectedly
                if (ClientManager.this.isClientRunning) {
                    InterfaceManager.displayWarning("Disconnected From Server!");
                }

                ClientManager.this.endClientConnection();
                // InterfaceManager.displayError(e, "Client Related Error.");

            }

            return messageObj;

        }

        // Tells the thread to sleep a certain amount of time
        private void wait(int ms) {

            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                InterfaceManager.displayError(e, "Thread sleep error occurred");
            }

        }

    }

    // #-----------------------------------------------------------------------#
    // #                     7 - Instruction Handler Class                     #
    // #-----------------------------------------------------------------------#

    private class InstructionHandler implements Runnable {

        public InstructionHandler() {
        }

        // #-------------------------------------------------------------------#
        // #                       7.1 - Runnable Method                       #
        // #-------------------------------------------------------------------#

        @Override
        public void run() {

            while (ClientManager.this.isClientRunning) {

                ClientInstruction instruction = ClientManager.this.instructionsQueue.getNextInstruction();

                if (instruction != null) {

                    switch (instruction.instructionType) {

                    case ClientInstruction.SEND_MESSAGE_INSTRUCTION_TYPE:

                        // Send Message To Server
                        this.processInstructionSendMessage(instruction.data);
                        break;

                    case ClientInstruction.BECOME_COORDINATOR_INSTRUCTION_TYPE:

                        // Set Client To Coordinator
                        this.processInstructionBecomeCoordinator();
                        break;

                    case ClientInstruction.REVOKE_COORDINATOR_INSTRUCTION_TYPE:

                        // Revoke Client As Coordinator
                        this.processInstructionRevokeCoordinator();
                        break;

                    case ClientInstruction.ESTABLISH_CONNECTION_INSTRUCTION_TYPE:

                        // Ask server to join network
                        this.processInstructionEstablishConnection(instruction.convertInstructionToString());
                        break;

                    case ClientInstruction.REVIEW_JOIN_REQUEST_INSTRUCTION_TYPE:

                        // Check if client is coordinator
                        if (ClientManager.this.isCoordinator) {

                            // Accept / Reject connection a new client's connection request
                            this.processInstructionReviewJoinRequest(instruction.data);

                        }

                        break;

                    case ClientInstruction.ADD_CLIENT_INFO_TO_LOCAL_LIST_INSTRUCTION_TYPE:

                        // Check client is not coordinator as coordinator sends this message to others
                        if (!ClientManager.this.isCoordinator) {

                            // Update local client info list
                            this.processInstructionAddClientInfoToLocalList(instruction.data);

                        }

                        break;

                    case ClientInstruction.NOTIFY_CLIENT_DISCONNECTED_INSTRUCTION_TYPE:

                        // Remove client ID from info list, if coordinator, tell other members
                        this.processInstructionNotifyClientDisconnected(instruction.data);
                        break;

                    case ClientInstruction.CLIENT_ACCEPTED_INSTRUCTION_TYPE:

                        // Connection accepted, tell ask coordinator for updated list of all other
                        // members
                        this.processInstructionClientAccepted(instruction.data);
                        break;

                    case ClientInstruction.GET_UPDATED_CLIENT_INFO_LIST_INSTRUCTION_TYPE:

                        // Ask for updated list of other clients in the channel
                        this.processInstructionGetUpdatedClientInfoList(instruction.data);
                        break;

                    case ClientInstruction.SET_LOCAL_CLIENT_INFO_LIST_INSTRUCTION_TYPE:

                        // Set local client info list
                        this.processInstructionSetLocalClientInfoList(instruction.data);
                        break;

                    case ClientInstruction.SEND_SERVER_CHAT_MESSAGE_INSTRUCTION_TYPE:

                        // Send message to everyone 
                        this.processInstructionSendServerChatMessage(instruction.data);
                        break;

                    case ClientInstruction.CONNECTION_REJECTED_BY_COORDINATOR_INSTRUCTION_TYPE:

                        // Process being rejected by the coordinator
                        this.processInstructionConnectionRejectedByCoordinator(instruction.data);
                        break;

                    case ClientInstruction.NOTIFY_OTHERS_OF_NEW_COORDINATOR:

                        // Process notifying other members of the new coordinator
                        this.processInstructionNotifyOthersOfNewCoordinator(instruction.data);
                        break;

                    default:
                        InterfaceManager.displayWarning(
                                "An unexpected instruction has been detected. Please contact your system administrator.");
                        break;

                    }

                }

                this.wait(100);

            }

        }

        // #-------------------------------------------------------------------#
        // #               7.2 - Specific Instruction Processing               #
        // #-------------------------------------------------------------------#

        // Processes the instruction 'Send Message Obj of Type Instruction'
        private void processInstructionSendMessage(String data) {

            String[] dataComponents = data.split("::");
            String receiver = dataComponents[0];
            String message = dataComponents[1];
            boolean isServerChatMessage = dataComponents[2].equals("true");

            this.sendMessage(receiver, message, isServerChatMessage);

        }

        // Processes the instruction 'Become Coordinator'
        private void processInstructionBecomeCoordinator() {

            ClientManager.this.isCoordinator = true;

            // Add self to client info if not already present
            if (!ClientManager.this.clientListLocal.containsKey(ClientManager.this.clientID)) {
                ClientManager.this.addClientInfoToLocalList(ClientManager.this.clientID, ClientManager.this.clientInfo);
            }

            // Set coordinator ID for self
            ClientManager.this.coordinatorID = ClientManager.this.clientID;

            InterfaceManager.updateCoordinator(ClientManager.this.coordinatorIDLabel,
                    ClientManager.this.getCoordinatorID());

            // Tell others you are the coordinator
            String notifyOfNewCoordinatorInstructionString = ClientInstruction
                    .createNotifyOthersOfNewCoordinatorInstructionString(ClientManager.this.clientID);

            for (String currentClientID : ClientManager.this.getAllClientIDsFromLocalList()) {

                // Do not send message back to the person who sent the message.
                if (!currentClientID.equals(ClientManager.this.clientID)) {

                    // Send message object to clients server chat
                    this.sendInstruction(currentClientID, notifyOfNewCoordinatorInstructionString);

                }

            }

            // NOTE leave the message box at the bottom as it would stop the execution 
            //      of the rest of the code until the user clicks OK
            InterfaceManager.displayWarning("You've been assigned to be the group coordinator!");

        }

        // Processes the instruction 'Revoke Coordinator'
        private void processInstructionRevokeCoordinator() {
            ClientManager.this.isCoordinator = false;
        }

        // Processes the instruction 'Establish Connection'
        private void processInstructionEstablishConnection(String instructionString) {

            // Request connection from coordinator
            this.sendInstruction("SERVER", instructionString);

        }

        // Process the instruction 'Review Join Request'
        private void processInstructionReviewJoinRequest(String data) {

            String[] dataComponents = data.split("::");
            String tempID = dataComponents[0];
            String clientID = dataComponents[1];
            String clientIP = dataComponents[2];
            int clientPort = Integer.parseInt(dataComponents[3]);

            // Check if client ID already in use
            if (ClientManager.this.clientListLocal.containsKey(clientID)) {

                // Reject Connection as client ID already in use
                // Tell client why they are being rejected
                String connectionRejectedInstructionString = ClientInstruction
                        .createConnectionRejectedByCoordinatorInstructionString(
                                "Connection Rejected! Client ID already in use!");
                this.sendInstruction(tempID, connectionRejectedInstructionString);

                // Tell server to reject connection
                String rejectJoinRequestString = ClientInstruction.createRejectJoinRequestInstructionString(tempID);
                this.sendInstruction("SERVER", rejectJoinRequestString);

            } else {

                // Accept Connection Request
                // Add connection to local list
                ClientInfo clientInfo = new ClientInfo(clientID, clientIP, clientPort);
                ClientManager.this.addClientInfoToLocalList(clientID, clientInfo);

                // Allow co-ordinator to contact user by adding to interface.
                InterfaceManager.createClient(ClientManager.this.messagePane, clientID);

                // Tell server to accept connection
                String acceptNewConnectionString = ClientInstruction
                        .createAcceptClientConnectionInstructionString(tempID, clientID);
                this.sendInstruction("SERVER", acceptNewConnectionString);

                // Tell client they have been accepted
                String clientAcceptedString = ClientInstruction
                        .createClientAcceptedInstructionString(ClientManager.this.clientID);
                this.sendInstruction(clientID, clientAcceptedString);

                // Tell client of coordinator ID
                String notifyClientOfCoordinatorString = ClientInstruction
                        .createNotifyOthersOfNewCoordinatorInstructionString(ClientManager.this.clientID);
                this.sendInstruction(clientID, notifyClientOfCoordinatorString);

                // Update client info server cache
                String updatedAllClientInfoListString = ClientManager.this.getAllClientsInfoFromLocalListAsString();
                String updateServerClientListCacheString = ClientInstruction
                        .createUpdateClientInfosServerCacheInstructionString(updatedAllClientInfoListString);
                this.sendInstruction("SERVER", updateServerClientListCacheString);

                // Message other members to add the new connection
                this.tellOthersToAddClientsInfo(clientInfo);

            }

        }

        // Process the instruction 'Add Client Info To Local List'
        private void processInstructionAddClientInfoToLocalList(String data) {

            String[] dataComponents = data.split("::");
            String clientID = dataComponents[0];
            String clientIP = dataComponents[1];
            int clientPort = Integer.parseInt(dataComponents[2]);

            ClientInfo clientInfo = new ClientInfo(clientID, clientIP, clientPort);
            ClientManager.this.addClientInfoToLocalList(clientID, clientInfo);

            InterfaceManager.createClient(ClientManager.this.messagePane, clientID);

        }

        // Process the instruction 'Notify Client Disconnected'
        private void processInstructionNotifyClientDisconnected(String data) {

            String[] dataComponents = data.split("::");
            String clientID = dataComponents[0];

            // Remove client from local client list
            ClientManager.this.removeClientInfoFromLocalList(clientID);

            // Remove User
            InterfaceManager.removeClient(ClientManager.this.messagePane, clientID);

            // If coordinator, tell others to remove the client as well
            if (ClientManager.this.isCoordinator) {

                for (String currentClientID : ClientManager.this.getAllClientIDsFromLocalList()) {

                    // Prevents coordinator from causing an infinite loop by sending the message to
                    // itself
                    if (!currentClientID.equals(ClientManager.this.clientID)) {

                        String notifyClientDisconnectString = ClientInstruction
                                .createNotifyClientDisconnectedInstructionString(clientID);
                        this.sendInstruction(currentClientID, notifyClientDisconnectString);

                    }

                }

            }

        }

        // Process the instruction 'Client Accepted'
        private void processInstructionClientAccepted(String coordinatorID) {

            // Get updated client list from coordinator
            String getUpdatedClientInfoListString = ClientInstruction
                    .createGetUpdatedClientInfoListInstructionString(ClientManager.this.clientID);
            this.sendInstruction(coordinatorID, getUpdatedClientInfoListString);

        }

        // Process the instruction "Get Updated Client Info List"
        private void processInstructionGetUpdatedClientInfoList(String sender) {

            String updatedAllClientInfoListString = ClientManager.this.getAllClientsInfoFromLocalListAsString();
            String setLocalClientInfoInstruction = ClientInstruction
                    .createSetLocalClientInfoListString(updatedAllClientInfoListString);

            this.sendInstruction(sender, setLocalClientInfoInstruction);

        }

        // Process the instruction "Set Local Client Info List"
        private void processInstructionSetLocalClientInfoList(String allClientInfoListString) {

            HashMap<String, ClientInfo> newLocalClientInfoList = ClientManager.this
                    .convertAllClientInfoStringToHashMap(allClientInfoListString);
            ClientManager.this.setLocalClientInfoList(newLocalClientInfoList);

            Iterator clientIterator = newLocalClientInfoList.entrySet().iterator();

            while (clientIterator.hasNext()) {
                Map.Entry individualClient = (Map.Entry) clientIterator.next();
                if (!individualClient.getKey().toString().equals(ClientManager.this.clientID)) {
                    InterfaceManager.createClient(ClientManager.this.messagePane, individualClient.getKey().toString());
                }
            }

        }

        // Process the instruction "Send Server Chat Message"
        private void processInstructionSendServerChatMessage(String messageObjStr) {

            // For each client send group chat message
            String senderID = Message.fromString(messageObjStr).sender;

            for (String currentClientID : ClientManager.this.getAllClientIDsFromLocalList()) {

                // Do not send message back to the person who sent the message.
                if (!currentClientID.equals(senderID)) {

                    // Send message object to clients server chat
                    this.sendMessage(currentClientID, messageObjStr, true);

                }

            }

        }

        // Process the instruction "Connection Rejected By Coordinator"
        private void processInstructionConnectionRejectedByCoordinator(String message) {

            ClientManager.this.endClientConnection();
            InterfaceManager.displayWarning(message);

        }

        // Process the instruction "NotifyOthersOfNewCoordiantor"
        private void processInstructionNotifyOthersOfNewCoordinator(String coordinatorID) {

            ClientManager.this.coordinatorID = coordinatorID;
            InterfaceManager.updateCoordinator(ClientManager.this.coordinatorIDLabel,
                    ClientManager.this.getCoordinatorID());

        }

        // Tells other members to add a client's infomation to their local client info
        // list
        private void tellOthersToAddClientsInfo(ClientInfo clientInfo) {

            String addNewClientToLocalListString = ClientInstruction.createAddClientInfoToLocalListInstructionString(
                    clientInfo.clientID, clientInfo.clientIP, clientInfo.clientPort);

            for (String currentClientID : ClientManager.this.getAllClientIDsFromLocalList()) {

                // Message all clients except itself (coordinator) and the new client that
                // joined
                if (!currentClientID.equals(clientInfo.clientID)
                        && !currentClientID.equals(ClientManager.this.clientID)) {
                    this.sendInstruction(currentClientID, addNewClientToLocalListString);
                }

            }

        }

        // #-------------------------------------------------------------------#
        // #               7.3 - Messaging and Thread Functions                #
        // #-------------------------------------------------------------------#

        // Tells the thread to sleep a certain amount of time
        private void wait(int ms) {

            try {
                Thread.sleep(ms);
            } catch (InterruptedException e) {
                InterfaceManager.displayError(e, "Thread sleep error occurred");
            }

        }

        // Sends a message to the server
        private void sendMessage(String receiver, String message, boolean isServerChatMessage) {

            Message messageObj = new Message(ClientManager.this.clientID, receiver, message, Message.MESSAGE_TYPE,
                    isServerChatMessage);
            this.transmitMessage(messageObj);

        }

        // Sends an instruction to the server
        private void sendInstruction(String receiver, String message) {

            Message messageObj = new Message(ClientManager.this.clientID, receiver, message, Message.INSTRUCTION_TYPE);
            this.transmitMessage(messageObj);

        }

        // Transmits a message object to the server
        private void transmitMessage(Message messageObj) {

            try {
                ClientManager.this.outputStream.writeObject(messageObj);
            } catch (IOException e) {
                InterfaceManager.displayError(e, "Message send failed.");
            }

        }

    }

}