package work.universitycourse.comp1549.Components;

import java.util.Deque;
import java.net.Socket;
import java.util.HashMap;
import java.sql.Timestamp;
import java.util.ArrayDeque;
import work.universitycourse.comp1549.Modules.ClientConnectionManager;

/**
 *
 * @author Adnan Turan
 * @author Daniel Browne
 * @author Gabriel Netz
 * @author William Phillips
 */
public class Channel {
    private static Channel channel;
    private Deque<Message> channelMessages = new ArrayDeque<Message>(); // Used as a Queue
    private HashMap<String, ClientConnectionManager> clientConnections = new HashMap<String, ClientConnectionManager>();
    private ClientConnectionManager coordinatorClientConnection = null;


    private Channel() {}

    public static Channel getChannel() {

        if (channel == null) {
            channel = new Channel();
        }
        return channel;

    }

    // Adds a message object to the channel after setting a timestamp for the message
    public void addMessage(Message messageObj) {

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        messageObj.timestamp = timestamp;
        this.channelMessages.addLast(messageObj);

    }

    // Gets next available message from the channelMessages, using FIFO
    public Message getNextMessage() {
        return this.channelMessages.poll();
    }

    // Returns clientConnection object using the client's ID
    public ClientConnectionManager getClientConnection(String clientID) {
        return this.clientConnections.get(clientID);
    }

    // Check if a clientConnection exists by checking the client's ID
    public boolean checkClientConnectionExists(String clientID) {
        return this.clientConnections.containsKey(clientID);
    }

    public String addClientConnection(Socket clientSocket) {

        // Create client connection object
        ClientConnectionManager clientConnectionObj = new ClientConnectionManager(clientSocket);
        String clientID = clientConnectionObj.getClientID();

        // Add client connection object to clientConnections hashmap
        this.clientConnections.put(clientID, clientConnectionObj);

        // Start thread to listen for new client messeges
        this.startClientThread(clientID);
        return clientID;
    }

    public void removeClientConnection(String clientID) {

        // Removes a client connection form the client hashmap
        if (this.checkClientConnectionExists(clientID)) {

            this.stopClientThread(clientID);
            this.clientConnections.remove(clientID);

        } else {
            // TODO how to handle an attempt of removing a non existing client
        }

    }

    public ClientConnectionManager getCoordinatorConnection() {
        return this.coordinatorClientConnection;
    }

    public String getCoordinatorID() {
        return this.coordinatorClientConnection.getClientID();
    }
    
    public void setCoordinatorConnection(String clientID) {
        // TODO If possible recheck client connection before setting or make server do it
        this.coordinatorClientConnection = this.clientConnections.get(clientID);
    }

    public void setCoordinatorConnectionNull() {
        this.coordinatorClientConnection = null;
    }

    private void startClientThread(String clientID) {
        new Thread(this.clientConnections.get(clientID)).start();
    }

    private void stopClientThread(String clientID) {
        this.clientConnections.get(clientID).stopThread();
    }

}
