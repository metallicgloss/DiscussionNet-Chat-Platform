package work.universitycourse.comp1549.Components;

import java.util.Deque;
import java.util.HashMap;
import java.util.ArrayDeque;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;

import java.net.Socket;

import java.sql.Timestamp;

import work.universitycourse.comp1549.Modules.InterfaceManager;

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
 *          Server Channel Message Handling
 *                          
 *             Client Connection Handling
 * 
 *                 Coordinator Handling
 * 
 *                Client Connection Class
 * 
 *                          > Constructor and Connection Setup
 *                          
 *                          > Runnable Method
 *                          
 *                          > Client ID Handling
 * 
 *                          > Message Handling
 *
 *                          > Thread Handling
 * 
 * 
 */

public class ServerChannel {
    private volatile Deque<Message> channelMessages = new ArrayDeque<Message>(); // Used as a Queue
    private HashMap<String, ClientConnection> clientConnections = new HashMap<String, ClientConnection>();
    private ClientConnection coordinatorClientConnection = null;
    // private HashMap<String, ClientDetail> clientDetailListCached = new HashMap<String, ClientDetail>();

    public ServerChannel() {}

    // ===================================================
    // -         Server Channel Message Handling         -
    // ===================================================

        // Sets a timestamp for a message objects and adds it to the channelMessages
        public void addMessageToChannel(Message messageObj) { // HACK USED TO BE addMessage(Message messageObj)

            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            messageObj.timestamp = timestamp;
            this.channelMessages.addLast(messageObj);

        }

        // Gets next available message from the channelMessages, using FIFO
        public Message getNextMessageFromChannel() { // HACK USED TO BE getNextMessage
            return this.channelMessages.poll();
        }

    // ===================================================
    // -           Client Connection Handling            -
    // ===================================================

        // Sends a message object to a client
        public void sendMessageToClient(String receiverID, Message messageObj) { // HACK USED TO BE ClientConnection getClientConnection(String clientID)
            this.clientConnections.get(receiverID).sendMessageToClient(messageObj);
        }

        // Returns True when a client connection object is found at the ID specified in clientConnections
        public boolean checkClientConnectionExists(String clientID) {
            return this.clientConnections.containsKey(clientID);
        }

        // Adds a new client connection to client connections and starts a thread to listen for incoming messages from the client
        public String addNewClientConnection(Socket clientSocket) { // HACK USED TO BE addClientConnection(Socket clientSocket)

            // Create client connection object
            ClientConnection clientConnectionObj = new ClientConnection(clientSocket);
            String clientID = clientConnectionObj.getClientID();

            // Add client connection object to clientConnections hashmap
            this.clientConnections.put(clientID, clientConnectionObj);

            //Start thread to listen for new client messages
            this.startClientConnectionThread(clientID);

            // TODO Tell coordinator a new member has joined

            return clientID;

        }

        // Removes a client connection form the clientConnections hashmap
        public void removeClientConnection(String clientID) {

            if (this.checkClientConnectionExists(clientID)) {

                this.stopClientConnectionThread(clientID);
                this.clientConnections.remove(clientID);

            } else {
                // TODO How to handle an attempt to remove a non existing client
            }

        }

        private void startClientConnectionThread(String clientID) {
            new Thread(this.clientConnections.get(clientID)).start();
        }

        private void stopClientConnectionThread(String clientID) {
            this.clientConnections.get(clientID).stopThread();
        }

    // ===================================================
    // -             Coordinator Handling                -
    // ===================================================

        // Returns True if the coordinator is set
        public boolean checkCoordinatorIsSet() { // HACK Used to be ClientConnection getCoordinatorConnection()
            return this.coordinatorClientConnection != null;
        }

        // Returns the ID of the coordinator client
        public String getCoordinatorID() {
            return this.coordinatorClientConnection.getClientID();
        }

        // Sets the coordinator client connection to be the client with the specified ID
        public void setCoordinatorConnection(String clientID) {
            // TODO If possible recheck client connection before setting or make server do it
             this.coordinatorClientConnection = this.clientConnections.get(clientID);
        }

        // Sets the coordinator client connection object to null
        public void setCoordinatorConnectionNull() {
            this.coordinatorClientConnection = null;
        }

    // ===================================================
    // -             Client Connection Class             -
    // ===================================================

        private class ClientConnection implements Runnable {

            private Socket clientSocket;
            private ObjectInputStream inputStream;
            private ObjectOutputStream outputStream;
            private String clientID;

            private volatile Deque<Message> channelMessages = ServerChannel.this.channelMessages;
            private boolean isThreadRunning = true;

            // ===================================================
            // -        Constructor and Connection Setup         -
            // ===================================================            

            public ClientConnection(Socket socket) {
                
                this.clientSocket = socket;
                
                this.setInputOutputStreams();
                this.setClientID();

            }

            // Sets the input and output streams for the client connection
            private void setInputOutputStreams() {

                try {
        
                    this.inputStream = new ObjectInputStream(this.clientSocket.getInputStream());
                    this.outputStream = new ObjectOutputStream(this.clientSocket.getOutputStream());
        
                } catch (IOException e) {
                    InterfaceManager.displayError(e, "Failed setting input / output streams! IOException error occurred.");
                }

            }

            // Closes the InputOutputStreams
            private void closeInputOutputStreams() {

                try {

                    this.outputStream.close();
                    this.inputStream.close();

                } catch (IOException e) {
                    InterfaceManager.displayError(e, "Failed to close streams! IOException error occurred.");
                }

            }

            // ===================================================
            // -                 Runnable Method                 -
            // ===================================================

            @Override
            public void run() {

                while (this.isThreadRunning) {
                    this.addMessageToChannel();
                }

                Thread.currentThread().interrupt();

            }

            // ===================================================
            // -                Client ID Handling               -
            // ===================================================

            // Returns the ID of the client linked to this specific connection
            private String getClientID() {
                return this.clientID;
            }

            // Sets the client ID for this specific connection
            private void setClientID() {
                // TODO Change this to check that client ID is valid or make server auto kick if someone already has the ID
                this.clientID = this.receiveMessage().message;
            }

            // ===================================================
            // -                 Message Handling                -
            // ===================================================

            // Adds a message object to the server channel
            private void addMessageToChannel() {

                Message response = this.receiveMessage();

                if (response != null) {

                    response.sender = this.clientID; // Done to ensure client does not try to fake their ID
                    ServerChannel.this.addMessageToChannel(response);

                }

            }

            // Receives messages for the client connection's input stream, will terminal the connection if an error occurs
            private Message receiveMessage() {

                Message data = null;

                try {
                    data = (Message) this.inputStream.readObject();
                } catch (IOException | ClassNotFoundException e) {

                    this.terminateConnection();

                }

                return data;

            }

            public void sendMessageToClient(Message messageObj) {  // HACK USED TO BE void sendMessage(Message message)

                try {
                    this.outputStream.writeObject(messageObj);
                } catch (IOException e) {
                    InterfaceManager.displayError(e, "Message Send Failed.");
                }

            }

            // ===================================================
            // -                 Thread Handling                 -
            // ===================================================

            private void terminateConnection() {
                
                this.closeInputOutputStreams();
                Message terminateClientInstruction = new Message("SERVER", "SERVER", "REMOVE CONNECTION<SEPERATOR>" + this.clientID, Message.INSTRUCTION_TYPE);
                ServerChannel.this.addMessageToChannel(terminateClientInstruction);
                this.isThreadRunning = false;

            }

            public void stopThread() {
                this.isThreadRunning = false;
            }

        }

}
