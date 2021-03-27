package work.universitycourse.comp1549.Components;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Timestamp;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import work.universitycourse.comp1549.Modules.InterfaceManager;

/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 *
 */

// #---------------------------------------------------------------------------#
// #                                  Contents                                 #
// #---------------------------------------------------------------------------#
// #                                                                           #
// #                               ServerChannel                               #
// #             Handles server channel and client communication.              #
// #                                                                           #
// #                   1 - Server Channel Message Handling                     #
// #                   2 - Client Connection Handling                          #
// #                   3 - Client Info Handling                                #
// #                   4 - Coordinator Handling                                #
// #                   5 - Client Connection Class                             #
// #                   5.1 - Constructor and Connection Setup                  #
// #                   5.2 - Runnable Method                                   #
// #                   5.3 - Client ID Handling                                #
// #                   5.4 - Message Handling                                  #
// #                   5.5 - Thread Handling                                   #
// #                   5.6 - Client Connections Handling                       #
// #                                                                           #
// #---------------------------------------------------------------------------#

public class ServerChannel {

    // Used as a message/instruction queue for each channel.
    private volatile Deque<Transmittable> channelMessages = new ArrayDeque<Transmittable>(); 

    private HashMap<String, ClientConnection> clientConnections = new HashMap<String, ClientConnection>();
    private ClientConnection coordinatorClientConnection = null;
    private HashMap<String, ClientInfo> clientInfoListCached = new HashMap<String, ClientInfo>();

    public ServerChannel() {
    }
    
    // #-----------------------------------------------------------------------#
    // #                  1 - Server Channel Message Handling                  #
    // #-----------------------------------------------------------------------#

    // Sets a timestamp for a transmittable object and adds it to the channelMessages
    public void addTransmittableToChannel(Transmittable transmittableObj) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        transmittableObj.timestamp = timestamp;
        this.channelMessages.addLast(transmittableObj);

    }

    // Gets next available transmittable from the channelMessages, using FIFO
    public Transmittable getNextTransmittableFromChannel() {
        return this.channelMessages.poll();
    }

    // #-----------------------------------------------------------------------#
    // #                  2 - Client Connection Handling                       #
    // #-----------------------------------------------------------------------#

    // Sends a transmittable object to a client
    public void sendTransmittableToClient(Transmittable transmittableObj) {
        this.clientConnections.get(transmittableObj.receiver).sendTransmittableToClient(transmittableObj);
    }

    // Returns True when a client connection object is found at the ID specified in clientConnections
    public boolean checkClientConnectionExists(String clientID) {
        return this.clientConnections.containsKey(clientID);
    }

    // Adds a new client connection to client connections and starts a thread to listen for incoming transmittables from the client
    public String addNewClientConnection(Socket clientSocket) {

        // Create client connection object
        ClientConnection clientConnectionObj = new ClientConnection(clientSocket);

        String clientID = clientConnectionObj.getClientID();

        // Add client connection object to clientConnections hashmap
        this.clientConnections.put(clientID, clientConnectionObj);

        // Add Client Info
        this.addNewClientInfo(clientID, clientSocket);

        //Start thread to listen for new client transmittables
        this.startClientConnectionThread(clientID);

        return clientID;

    }

    // Terminates a clients connection
    public void terminateClientConnection(String clientID) {
        this.clientConnections.get(clientID).stopThread();
    }

    // Removes a client connection form the clientConnections hashmap
    public void removeClientConnection(String clientID) {

        if (this.checkClientConnectionExists(clientID)) {

            this.stopClientConnectionThread(clientID);
            this.clientConnections.remove(clientID);
            this.clientInfoListCached.remove(clientID);

        }

    }

    // Initiate new client connection.
    private void startClientConnectionThread(String clientID) {
        new Thread(this.clientConnections.get(clientID)).start();
    }

    // Close client connection.
    private void stopClientConnectionThread(String clientID) {
        this.clientConnections.get(clientID).stopThread();
    }

    // Rename the client ID from current to new string.
    public void renameClientID(String currentID, String newID) {

        // Change ID in the client connection object
        this.clientConnections.get(currentID).setClientID(newID);

        // Change ID in the client connections hashmap
        ClientConnection clientConnection = this.clientConnections.get(currentID);
        this.clientConnections.remove(currentID);
        this.clientConnections.put(newID, clientConnection);

        // Change ID in the clientInfoListCache
        ClientInfo clientInfo = this.clientInfoListCached.get(currentID);
        clientInfo.clientID = newID;
        this.clientInfoListCached.remove(currentID);
        this.clientInfoListCached.put(newID, clientInfo);

    }

    // #-----------------------------------------------------------------------#
    // #                  3 - Client Info Handling                             #
    // #-----------------------------------------------------------------------#

    // Creates a clientInfo object for a client and adds it to the clientInfoListCached
    private void addNewClientInfo(String clientID, Socket clientSocket) {

        ClientInfo clientInfo = new ClientInfo(clientID, clientSocket);
        this.clientInfoListCached.put(clientID, clientInfo);

    }

    // Returns the Info of a specific client
    public ClientInfo getClientInfo(String clientID) {
        return this.clientInfoListCached.get(clientID);
    }

    // Returns all the clientIDs in the cached client info list
    public Set<String> getAllConnectedClientIDs() {
        return this.clientInfoListCached.keySet();
    }

    // Returns the hashmap of off the local client info list
    public HashMap<String, ClientInfo> getAllClientInfo() {
        return this.clientInfoListCached;
    }

    // Sets the client info list cache on the server to a specified one
    public void setClientInfoListCache(HashMap<String, ClientInfo> newClientInfoList) {
        this.clientInfoListCached = newClientInfoList;
    }

    // #-----------------------------------------------------------------------#
    // #                  4 - Coordinator Handling                             #
    // #-----------------------------------------------------------------------#

    // Returns True if the coordinator is set
    public boolean checkCoordinatorIsSet() {
        return this.coordinatorClientConnection != null;
    }

    // Returns the ID of the coordinator client
    public String getCoordinatorID() {

        String clientID = null;

        if (this.coordinatorClientConnection != null) {
            clientID = this.coordinatorClientConnection.getClientID();
        }

        return clientID;

    }

    // Sets the coordinator client connection to be the client with the specified ID
    public void setCoordinatorConnection(String clientID) {
        this.coordinatorClientConnection = this.clientConnections.get(clientID);
    }

    // Sets the coordinator client connection object to null
    public void setCoordinatorConnectionNull() {
        this.coordinatorClientConnection = null;
    }

    // #-----------------------------------------------------------------------#
    // #                  5 - Client Connection Class                          #
    // #-----------------------------------------------------------------------#

    private class ClientConnection implements Runnable {

        private Socket clientSocket;
        private ObjectInputStream inputStream;
        private ObjectOutputStream outputStream;
        private String clientID;
        private boolean isThreadRunning = true;

        // #-------------------------------------------------------------------#
        // #              5.1 - Constructor and Connection Setup               #
        // #-------------------------------------------------------------------#    

        public ClientConnection(Socket socket) {

            this.clientSocket = socket;

            this.setInputOutputStreams();
            this.clientID = UUID.randomUUID().toString();

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

        // #-------------------------------------------------------------------#
        // #              5.2 - Runnable Method                                #
        // #-------------------------------------------------------------------#

        @Override
        public void run() {

            while (this.isThreadRunning) {
                this.addTransmittableToChannel();
            }

            this.closeInputOutputStreams();
            Thread.currentThread().interrupt();

        }

        // #-------------------------------------------------------------------#
        // #              5.3 - Client ID Handling                             #
        // #-------------------------------------------------------------------#

        // Returns the ID of the client linked to this specific connection
        private String getClientID() {
            return this.clientID;
        }

        // Sets the client ID for this specific connection
        private void setClientID(String clientID) {
            this.clientID = clientID;
        }

        // #-------------------------------------------------------------------#
        // #              5.4 - Message Handling                               #
        // #-------------------------------------------------------------------#

        // Adds a transmittable object to the server channel
        private void addTransmittableToChannel() {

            Transmittable response = this.receiveTransmittable();

            if (response != null) {

                response.sender = this.clientID; // Done to ensure client does not try to fake their ID
                ServerChannel.this.addTransmittableToChannel(response);

            }

        }

        // Receives transmittables for the client connection's input stream, will terminal the connection if an error occurs
        private Transmittable receiveTransmittable() {

            Transmittable data = null;

            try {
                data = (Transmittable) this.inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {

                this.terminateConnection();

            }

            return data;

        }

        // Sends a transmittable object via the object output stream
        public void sendTransmittableToClient(Transmittable transmittableObj) {

            try {
                this.outputStream.writeObject(transmittableObj);
            } catch (IOException e) {
                InterfaceManager.displayError(e, "Transmittable Send Failed - unhandled. Please try again.");
            }

        }

        // #-------------------------------------------------------------------#
        // #              5.5 - Thread Handling                                #
        // #-------------------------------------------------------------------#

        // Handle connection termination.
        private void terminateConnection() {

            this.closeInputOutputStreams();

            // Check client is not temporary
            if (ServerChannel.this.getAllConnectedClientIDs().contains(this.clientID)) {

                try {
                    
                    // Tell server a connected client left the server
                    String clientDisconnectedInstructionString = ClientInstruction
                            .createClientDisconnectedInstructionString(this.clientID);

                    ClientInstruction terminateClientInstruction = new ClientInstruction("SERVER", "SERVER",
                            clientDisconnectedInstructionString);

                    ServerChannel.this.addTransmittableToChannel(terminateClientInstruction);
                    
                } catch (ClientInstruction.InstructionNotExistException
                    | ClientInstruction.InstructionFormatException
                    | ClientInstruction.DataFormatException e) {
                }

            }

            this.stopThread();

        }

        // Stop active thread.
        public void stopThread() {

            this.isThreadRunning = false;

            try {
                this.clientSocket.close();
            } catch (IOException e) {
                InterfaceManager.displayError(e, "Unhandled IOException error stopping thread.");
            }

        }

    }

}
